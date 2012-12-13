/*******************************************************************************
 * Copyright (c) 2012 Ericsson
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Miles Parker (Tasktop Technologies) - primary API and implementation
 *     Git Hub, Inc. - fixes for bug 354570
 *******************************************************************************/

package org.eclipse.mylyn.internal.reviews.ui.providers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.preference.JFacePreferences;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.mylyn.commons.core.DateUtil;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.mylyn.internal.reviews.ui.ReviewsImages;
import org.eclipse.mylyn.internal.reviews.ui.ReviewsUiPlugin;
import org.eclipse.mylyn.reviews.core.model.IComment;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.IFileRevision;
import org.eclipse.mylyn.reviews.core.model.ILineLocation;
import org.eclipse.mylyn.reviews.core.model.ILocation;
import org.eclipse.mylyn.reviews.core.model.IReview;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.core.model.IReviewItemSet;
import org.eclipse.mylyn.reviews.core.model.ITaskReference;
import org.eclipse.mylyn.reviews.core.model.ITopic;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;
import org.eclipse.mylyn.reviews.core.model.IUser;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * Base support for reviews labels.
 * 
 * @author Miles Parker
 * @author Steffen Pingel
 * @author Kevin Sawicki
 */
public abstract class ReviewsLabelProvider extends TableStyledLabelProvider {

	private static final int TOOLTIP_CHAR_WIDTH = 100;

	static final int LINE_NUMBER_WIDTH = 4;

	static final int MAXIMUM_COMMENT_LENGTH = 300;

	final Styler authorStyle = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.font = JFaceResources.getTextFont();
			textStyle.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		}
	};

	final Styler commentStyle = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.font = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
		}
	};

	final Styler commentDateStyle = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.font = JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT);
			ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
			textStyle.foreground = colorRegistry.get(JFacePreferences.DECORATIONS_COLOR);
		}
	};

	public final Styler lineNumberStyler = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
			textStyle.foreground = colorRegistry.get(JFacePreferences.COUNTER_COLOR);
			textStyle.font = JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT);
		}
	};

	static final SimpleDateFormat COMMENT_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm a");

	@Override
	public Image getImage(Object element) {
		if (element instanceof IReview) {
			return ReviewsUiPlugin.getDefault().getImageManager().getFileImage("review");
		}
		if (element instanceof IReviewItemSet) {
			return CommonImages.getImage(ReviewsImages.CHANGE_LOG);

		}
		if (element instanceof IReviewItem) {
			IReviewItem item = (IReviewItem) element;
			return ReviewsUiPlugin.getDefault().getImageManager().getFileImage(item.getName());
		}
		if (element instanceof IComment) {
			//See https://bugs.eclipse.org/bugs/show_bug.cgi?id=334967#c16
//			IComment comment = (IComment) element;
//			if (StringUtils.startsWith(comment.getAuthor().getDisplayName(), "Hudson")) {
//				return CommonImages.getImage(ReviewsImages.SERVER);
//			}
			//TODO: We'd like to return PERSON_ME if user, but need to figure out how to get that w/o creating too much coupling.
			return CommonImages.getImage(CommonImages.PERSON);
		}
		if (element instanceof GlobalCommentsNode) {
			return CommonImages.getImage(TasksUiImages.TASK);
		}
		return null;
	}

	public String getToolTipText(Object element) {
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			String desc = comment.getDescription();
			desc = StringUtils.replace(desc, "\r\n", "\n");
			String[] lines = desc.split("\n");
			List<String> seperated = new ArrayList<String>();
			for (String line : lines) {
				String newLine = "";
				String[] splitByWholeSeparator = StringUtils.splitByWholeSeparator(line, " ");
				int count = 0;
				for (String word : splitByWholeSeparator) {
					count += word.length();
					if (count > TOOLTIP_CHAR_WIDTH) {
						seperated.add(newLine);
						newLine = word;
						count = word.length();
					} else {
						if (count > word.length()) {
							newLine += " ";
						}
						newLine += word;
					}
				}
				seperated.add(newLine);
			}
			return StringUtils.join(seperated, "\n");
		}
		return null;
	}

	protected IFileItem getFile(Object element) {
		if (element instanceof IFileItem) {
			return (IFileItem) element;
		}
		if (element instanceof IFileRevision) {
			return ((IFileRevision) element).getFile();
		}
		if (element instanceof ITopic) {
			ITopic topic = (ITopic) element;
			return getFile(topic.getItem());
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof GlobalCommentsNode) {
			return "Global";
		}
		if (element instanceof Collection) {
			Collection<?> collection = (Collection<?>) element;
			if (collection.size() == 1) {
				return getText(collection.iterator().next());
			}
		}
		if (element instanceof ITaskReference) {
			ITaskReference ref = (ITaskReference) element;
			return ref.getTaskId();
		}
		if (element instanceof IReview) {
			IReview review = (IReview) element;
			ITaskReference reviewTask = review.getReviewTask();
			if (reviewTask != null) {
				return getText(reviewTask);
			}
			return "Change " + review.getId();
		}
		if (element instanceof IFileRevision) {
			IFileRevision revision = (IFileRevision) element;
			String text = getText(revision.getFile());
			text += revision.getName();
			return text;
		}
		if (element instanceof IFileItem) {
			//Note, we want platform independent separator here.
			return StringUtils.substringAfterLast(((IFileItem) element).getName(), "/");
		}
		if (element instanceof IReviewItem) {
			return ((IReviewItem) element).getName();
		}
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			String desc = comment.getDescription();
			desc = StringUtils.normalizeSpace(desc);
			return desc;
		}
		if (element instanceof IUser) {
			return ((IUser) element).getDisplayName();
		}
		if (element instanceof Date) {
			return DateUtil.getRelativeDuration(System.currentTimeMillis() - ((Date) element).getTime()) + " ago";
		}
		if (element instanceof ILineLocation) {
			int min = ((ILineLocation) element).getRangeMin();
			int max = ((ILineLocation) element).getRangeMax();
			String text = min + "";
			if (min != max) {
				text += "-" + max;
			}
			return text;
		}
		return super.getText(element);
	}

	public StyledString getStyledText(Object element) {
		String text = getText(element);
		StyledString styledString = new StyledString();
		if (text != null) {
			styledString.append(text);
			if (element instanceof GlobalCommentsNode) {
				element = ((GlobalCommentsNode) element).getReview();
			}
			addTopicContainerStatsStyle(element, styledString);
			addFilePathStyle(element, styledString);
			addTopicLineNumberStyle(element, styledString);
		} else {
			styledString.append(element.toString());
		}
		return styledString;
	}

	protected void addTopicLineNumberStyle(Object element, StyledString styledString) {
		if (element instanceof ITopic) {
			ITopic comment = (ITopic) element;
			String[] locationStrings = new String[comment.getLocations().size()];
			int index = 0;
			for (ILocation location : comment.getLocations()) {
				locationStrings[index++] = getText(location);
			}
			String locationString = StringUtils.join(locationStrings, ", ");
			styledString.append(locationString + "", lineNumberStyler);
		}
	}

	protected void addFilePathStyle(Object element, StyledString styledString) {
		if (element instanceof IFileItem) {
			IReviewItem item = (IReviewItem) element;
			styledString.append("  " + item.getName(), StyledString.QUALIFIER_STYLER);
		}
	}

	protected void addTopicContainerStatsStyle(Object element, StyledString styledString) {
		if (element instanceof ITopicContainer) {
			ITopicContainer container = (ITopicContainer) element;
			String statsText = getStatsText(container);
			styledString.append(statsText, StyledString.DECORATIONS_STYLER);
		}
	}

	@Override
	protected StyledString getColumnStyledText(Object element, int index) {
		if (index == 0) {
			return getStyledText(element);
		}
		if (index == 1 && element instanceof IComment) {
			return new StyledString(getIndexText((IComment) element), lineNumberStyler);
		}
		return super.getColumnStyledText(element, index);
	};

	String getIndexText(IComment comment) {
		long index = comment.getIndex();
		return index < Long.MAX_VALUE ? index + "" : "";
	}

	String getStatsText(ITopicContainer container) {
		List<? extends IComment> comments;
		if (container instanceof IReview) {
			comments = container.getTopics();
		} else {
			comments = container.getAllComments();
		}
		int commentCount = comments.size();
		int draftCount = 0;
		for (IComment comment : comments) {
			if (comment.isDraft()) {
				draftCount += 1;
			}
		}
		commentCount -= draftCount;
		String statsText = "";
		if (commentCount > 0 && draftCount > 0) {
			statsText = NLS.bind("  [{0} comments, {1} drafts]", commentCount, draftCount);
		} else if (commentCount > 0) {
			statsText = NLS.bind("  [{0} comments]", commentCount);
		} else if (draftCount > 0) {
			statsText = NLS.bind("  [{0} drafts]", draftCount);
		}
		return statsText;
	}
}
