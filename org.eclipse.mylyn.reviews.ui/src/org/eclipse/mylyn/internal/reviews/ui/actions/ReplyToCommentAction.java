/*******************************************************************************
 * Copyright (c) 2009 Atlassian and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlassian - initial API and implementation
 ******************************************************************************/

package org.eclipse.mylyn.internal.reviews.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.LineRange;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.mylyn.internal.reviews.ui.annotations.IReviewCompareSourceViewer;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.ui.ReviewBehavior;
import org.eclipse.ui.IEditorInput;

/**
 * Action for adding a comment to a line in the active review
 * 
 * @author Shawn Minto
 */
public class ReplyToCommentAction extends Action implements ISelectionChangedListener {

	private final IReviewCompareSourceViewer compareSourceViewer;

	private IEditorInput editorInput;

	private LineRange selectedRange;

	public ReplyToCommentAction(IReviewCompareSourceViewer compareSourceViewer) {
		super("Reply to Comment...");
		this.compareSourceViewer = compareSourceViewer;
	}

	public void selectionChanged(SelectionChangedEvent event) {
		if (compareSourceViewer != null) {
			if (event.getSelection() instanceof TextSelection) {
				TextSelection sel = (TextSelection) event.getSelection();
//				List<CommentAnnotation> annotations = compareSourceViewer.getAnnotationModel().getAnnotationsWithin(
//						compareSourceViewer.getsel);
//				if (annotations.size() > 0) {
//					setEnabled(true);
//				}
			}
		}
		setEnabled(false);
	}

	@Override
	public String getToolTipText() {
		return "Adds a Review Comment For the Selected Line";
	}

	public void run(IAction action) {
		IReviewItem item = compareSourceViewer.getAnnotationModel().getItem();
		ReviewBehavior reviewBehavior = compareSourceViewer.getAnnotationModel().getBehavior();
	}
}
