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

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.mylyn.reviews.core.model.IComment;
import org.eclipse.mylyn.reviews.core.model.IDated;
import org.eclipse.mylyn.reviews.core.model.IFileRevision;
import org.eclipse.mylyn.reviews.core.model.IReview;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.core.model.ITopic;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * Provides table columns for the tree presentation, displaying description (e.g. "Global", patch set name, file name
 * and path, etc..), Location (e.g. line number), Author, and Last Change date. Dates display time elapsed since last
 * item change.
 * 
 * @author Miles Parker
 */
public class ReviewsTreeLabelProvider extends ReviewsLabelProvider {

	@Override
	public boolean isStyledText(int column) {
		return column == 0;
	}

	@Override
	public Image getColumnImage(Object element, int column) {
		if (column == 0) {
			return getImage(element);
		}
		return null;
	}

	@Override
	public StyledString getStyledText(Object element) {
		if (element instanceof ITopic) {
			return new StyledString(getText(element), commentStyle);
		}
		return super.getStyledText(element);
	}

	@Override
	protected StyledString getColumnStyledText(Object element, int column) {
		if (column == 1) {
			StyledString styledString = new StyledString();
			addTopicLineNumberStyle(element, styledString);
			return styledString;
		}
		return super.getColumnStyledText(element, column);
	}

	public String getColumnText(Object element, int column) {
		if (element instanceof IDated) {
			switch (column) {
			case 3:
				IDated item = (IDated) element;
				if (item.getModificationDate() != null) {
					return getText(item.getModificationDate());
				}
				if (item.getCreationDate() != null) {
					return getText(item.getCreationDate());
				}
			}
		}
		if (element instanceof GlobalCommentsNode) {
			return getColumnText(((GlobalCommentsNode) element).getReview(), column);
		}
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			switch (column) {
			case 0:
				return comment.getDescription();
			case 1:
				return getIndexText(comment);
			case 2:
				return getText(comment.getAuthor());
			}
		}
		if (element instanceof IReviewItem) {
			IReviewItem item = (IReviewItem) element;
			switch (column) {
			case 2:
				return getText(item.getAddedBy());
			}
		}
		if (element instanceof IReview) {
			IReview item = (IReview) element;
			switch (column) {
			case 2:
				return getText(item.getOwner());
			}
		}
		if (element instanceof IFileRevision) {
			IFileRevision item = (IFileRevision) element;
			switch (column) {
			case 3:
				return getText(item.getRevision());
			}
		}
		if (column == 0) {
			String text = getText(element);
			if (element instanceof GlobalCommentsNode) {
				element = ((GlobalCommentsNode) element).getReview();
			}
			if (element instanceof ITopicContainer) {
				text += getStatsText((ITopicContainer) element);
			}
			return text;
		}
		return null;
	}

	@Override
	public int getWidth(int column) {
		switch (column) {
		case 0:
			return 800;
		case 1:
			return 75;
		case 2:
			return 100;
		case 3:
			return 120;
		}
		return 200;
	}

	@Override
	public String getLabel(int column) {
		switch (column) {
		case 0:
			return "Description";
		case 1:
			return "Location";
		case 2:
			return "Author";
		case 3:
			return "Last Change";
		}
		return null;
	}

	@Override
	public String getColumnToolTipText(Object element, int column) {
		switch (column) {
		case 0:
			if (element instanceof IComment) {
				return getToolTipText(element);
			}
		}
		return super.getColumnToolTipText(element, column);
	}

	@Override
	public int getSwtStyle(int column) {
		switch (column) {
		case 1:
			return SWT.RIGHT;
		}
		return SWT.LEFT;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
}
