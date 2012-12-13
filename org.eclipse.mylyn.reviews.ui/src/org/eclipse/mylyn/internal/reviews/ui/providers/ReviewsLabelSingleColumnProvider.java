/*******************************************************************************
 * Copyright (c) 2012 Ericsson
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Miles Parker (Tasktop Technologies) - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.reviews.ui.providers;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.mylyn.reviews.core.model.IComment;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.ILineLocation;
import org.eclipse.mylyn.reviews.core.model.IReview;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.core.model.ITaskReference;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;
import org.eclipse.mylyn.reviews.core.model.IUser;

/**
 * Provides styled label support for list presentations such as within editors. Shows file name, number of
 * comments/drafts if applicable, and full file path as decoration.
 * 
 * @author Miles Parker
 */
public class ReviewsLabelSingleColumnProvider extends ReviewsLabelProvider {

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
		if (element instanceof IFileItem) {
			IFileItem file = (IFileItem) element;
			String fileName = StringUtils.substringAfterLast(file.getName(), File.separator);
			return fileName;
		}
		if (element instanceof IReviewItem) {
			return ((IReviewItem) element).getName();
		}
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			String desc = comment.getDescription();
			return desc;
		}
		if (element instanceof IUser) {
			IUser user = (IUser) element;
			return user.getDisplayName();
		}
		if (element instanceof Date) {
			return COMMENT_DATE_FORMAT.format(element);
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

	@Override
	public StyledString getStyledText(Object element) {
		String text = getText(element);
		StyledString styledString = new StyledString();
		if (text != null) {
			if (element instanceof IComment) {
				IComment comment = (IComment) element;
				String commentText = text;
				int textLength = 0;
				String authorText = "";
				if (comment.getAuthor() != null) {
					authorText = getText(comment.getAuthor());
					textLength += authorText.length();
				}
				int descSpaceRemaining = (MAXIMUM_COMMENT_LENGTH - textLength);
				if (commentText.length() > descSpaceRemaining + 3) { //Account for ellipses
					commentText = commentText.substring(0, descSpaceRemaining - 3) + "..."; //$NON-NLS-1$
				}
				commentText = StringUtils.rightPad(commentText, MAXIMUM_COMMENT_LENGTH - textLength);

				styledString.append(authorText + " ", authorStyle);
				styledString.append(commentText + " ", commentStyle);
				styledString.append(getText(comment.getCreationDate()) + " ", commentDateStyle);
			} else {
				styledString.append(text);
			}
			if (element instanceof GlobalCommentsNode) {
				element = ((GlobalCommentsNode) element).getReview();
			}
			if (element instanceof ITopicContainer) {
				ITopicContainer container = (ITopicContainer) element;
				String statsText = getStatsText(container);
				styledString.append(statsText, StyledString.DECORATIONS_STYLER);
			}
			if (element instanceof IFileItem) {
				IReviewItem item = (IReviewItem) element;
				styledString.append("  " + item.getName(), StyledString.QUALIFIER_STYLER);
			}
		} else {
			styledString.append(element.toString());
		}
		return styledString;
	}

	public String getColumnText(Object element, int columnIndex) {
		return getText(element);
	}

	@Override
	public String getLabel(int column) {
		return "Item";
	}

	@Override
	public int getWidth(int column) {
		return 300;
	}

	@Override
	public int getColumnCount() {
		// ignore
		return 1;
	}

}
