/*******************************************************************************
 * Copyright (c) 2012 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.gerrit.ui.editor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.mylyn.internal.gerrit.core.GerritUtil;
import org.eclipse.mylyn.internal.gerrit.core.client.GerritChange;
import org.eclipse.mylyn.internal.gerrit.core.client.GerritPatchSetContent;
import org.eclipse.mylyn.internal.gerrit.ui.GerritImages;
import org.eclipse.mylyn.internal.gerrit.ui.editor.PatchSetSection.PatchSetListener;
import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.mylyn.internal.tasks.ui.views.TaskListToolTip;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.ILineRange;
import org.eclipse.mylyn.reviews.core.model.ILocation;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.core.model.IReviewItemSet;
import org.eclipse.mylyn.reviews.core.model.ITopic;
import org.eclipse.mylyn.reviews.internal.core.model.LineLocation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.gerrit.common.data.PatchSetDetail;

public class InlineCommentsSection extends AbstractGerritSection {
	private TableViewer viewer;

	protected PatchSetSection patchSetSection;

	private static class InlineComment {
		private final IReviewItemSet reviewItemSet;

		private final IFileItem item;

		private final ITopic comment;

		private final boolean isTargetComment;

		public InlineComment(IReviewItemSet reviewItemSet, IFileItem item, ITopic comment, boolean isTargetComment) {
			this.reviewItemSet = reviewItemSet;
			this.item = item;
			this.comment = comment;
			this.isTargetComment = isTargetComment;
		}

		public TextSelection getRange() {
			ILocation location = comment.getLocation();
			if (location instanceof LineLocation && !((LineLocation) location).getRanges().isEmpty()) {
				ILineRange range = ((LineLocation) location).getRanges().get(0);
				int startLine = range.getStart() - 1;
				Document doc = new Document(isTargetComment ? item.getTarget().getContent() : item.getBase()
						.getContent());
				try {
					return new TextSelection(doc.getLineOffset(startLine), doc.getLineLength(startLine));
				} catch (BadLocationException e) {
					// ignore
				}
			}
			return null;
		}

		public String getContext() {
			ILocation location = comment.getLocation();
			if (location instanceof LineLocation && !((LineLocation) location).getRanges().isEmpty()) {
				ILineRange range = ((LineLocation) location).getRanges().get(0);
				int commentLine = range.getStart() - 1;
				int startLine = commentLine - 2;
				int endLine = commentLine + 2;
				Document doc = new Document(isTargetComment ? item.getTarget().getContent() : item.getBase()
						.getContent());
				try {
					// remove leading whitespace and blank lines.
					int minIndent = Integer.MAX_VALUE;
					List<String> lines = new ArrayList<String>();
					for (int i = Math.max(0, startLine); i <= Math.min(doc.getNumberOfLines(), endLine); i++) {
						String line = doc.get(doc.getLineOffset(i), doc.getLineLength(i));
						lines.add(line);
						if (line.trim().isEmpty()) {
							continue;
						}
						String[] parts = line.split("\\s+"); //$NON-NLS-1$
						if (parts.length > 1 && parts[0].length() == 0) {
							char firstNonWhitespaceChar = parts[1].charAt(0);
							int indent = line.indexOf(firstNonWhitespaceChar);
							if (indent < minIndent) {
								minIndent = indent;
							}
						} else {
							minIndent = 0;
						}
					}
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < lines.size(); i++) {
						String line = lines.get(i).substring(minIndent);
						if (i + startLine == commentLine) {
							sb.append("> " + line); //$NON-NLS-1$
						} else if (line.trim().length() != 0) {
							sb.append("    " + line); //$NON-NLS-1$
						}
					}
					return sb.toString().replace("\t", "      "); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (BadLocationException e) {
					// ignore
				}
			}
			return ""; //$NON-NLS-1$
		}

	}

	public InlineCommentsSection() {
		setPartName("Inline Comments");
	}

	@Override
	protected Control createContent(FormToolkit toolkit, Composite parent) {
		initializePatchSetSection();
		if (patchSetSection != null) {
			patchSetSection.addPatchSetListener(new PatchSetListener() {
				@Override
				public void patchSetUpdated() {
					updateComments();
				}
			});
		}
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(composite);
		viewer = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.NO_SCROLL | SWT.VIRTUAL);
		GridDataFactory.fillDefaults().grab(true, true).hint(500, SWT.DEFAULT).applyTo(viewer.getControl());
		EditorUtil.addScrollListener(viewer.getTable());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider() {

			@Override
			public void addListener(ILabelProviderListener listener) {
				// ignore

			}

			@Override
			public void dispose() {
				// ignore

			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				// ignore
				return false;
			}

			@Override
			public void removeListener(ILabelProviderListener listener) {
				// ignore

			}

			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				return CommonImages.getImage(GerritImages.GLASSES);
			}

			@Override
			public String getColumnText(Object element, int columnIndex) {
				if (element instanceof InlineComment) {
					ITopic comment = ((InlineComment) element).comment;
					return comment.getAuthor().getDisplayName() + ": " + comment.getDescription(); //$NON-NLS-1$
				}
				return null;
			}
		});
		viewer.addOpenListener(new IOpenListener() {

			@Override
			public void open(OpenEvent event) {
				Object element = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (element instanceof InlineComment) {
					InlineComment comment = (InlineComment) element;
					patchSetSection.doOpen(comment.reviewItemSet, comment.item, comment.getRange());
				}
			}
		});
		TaskListToolTip tip = new TaskListToolTip(viewer.getControl()) {
			private InlineComment currentItem;

			@Override
			protected boolean shouldCreateToolTip(Event event) {
				Widget tipWidget = getTipWidget(event);
				if (tipWidget instanceof TableItem) {
					currentItem = (InlineComment) tipWidget.getData();
					return true;
				} else {
					hide();
					return false;
				}
			}

			@Override
			protected Composite createToolTipArea(Event event, Composite parent) {
				Composite composite = createToolTipContentAreaComposite(parent);
				String date = new SimpleDateFormat().format(currentItem.comment.getCreationDate());
				addIconAndLabel(composite, CommonImages.getImage(GerritImages.GLASSES), currentItem.comment.getAuthor()
						.getDisplayName() + "  " + date, true);
				String path = currentItem.item.getName();
				String name = path;
				int slash = path.lastIndexOf('/');
				{
					name = path.substring(slash + 1);
				}
				String text = name + " - Patch Set " + currentItem.reviewItemSet.getId(); //$NON-NLS-1$
				addIconAndLabel(composite, null, text, false);
				addIconAndLabel(composite, null, currentItem.comment.getDescription(), false);
				String context = currentItem.getContext();
				if (!StringUtils.isEmpty(context)) {
					addIconAndLabel(composite, null, "Comment Context:", true);
					addIconAndLabel(composite, null, context, false);
				}
				return composite;
			}
		};
		viewer.getControl().setToolTipText(""); //$NON-NLS-1$
		updateComments();
		getTaskEditorPage().reflow();
		return composite;
	}

	protected void initializePatchSetSection() {
		patchSetSection = (PatchSetSection) getTaskEditorPage().getPart(PatchSetSection.class.getName());
	}

	private void updateComments() {
		if (patchSetSection == null) {
			return;
		}
		List<InlineComment> comments = new ArrayList<InlineComment>();
		final GerritChange change = GerritUtil.getChange(getTaskData());
		if (change != null) {
			for (final PatchSetDetail patchSetDetail : change.getPatchSetDetails()) {
				GerritPatchSetContent content = patchSetSection.getCache()
						.getContent(patchSetDetail.getInfo().getKey());
				if (content == null) {
					continue;
				}
				IReviewItemSet reviewItemSet = GerritUtil.createInput(change.getChangeDetail(), content,
						patchSetSection.getCache());
				for (IReviewItem item : reviewItemSet.getItems()) {
					if (item instanceof IFileItem) {
						for (ITopic comment : ((IFileItem) item).getBase().getTopics()) {
							comments.add(new InlineComment(reviewItemSet, (IFileItem) item, comment, false));
						}
						for (ITopic comment : ((IFileItem) item).getTarget().getTopics()) {
							comments.add(new InlineComment(reviewItemSet, (IFileItem) item, comment, true));
						}
					}
				}
			}
		}
		viewer.setInput(comments.toArray(new InlineComment[comments.size()]));
		((GridData) viewer.getControl().getLayoutData()).heightHint = comments.size() > 12 ? 225 : SWT.DEFAULT;
	}

	@Override
	protected boolean shouldExpandOnCreate() {
		return false;
	}

}
