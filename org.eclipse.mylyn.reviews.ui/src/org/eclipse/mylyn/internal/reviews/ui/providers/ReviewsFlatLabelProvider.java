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
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * Provides table columns for the flat presentation, displaying comment, author, artifact/file (if appropriate), line
 * location, and date. Dates display time elapsed since last item change.
 * 
 * @author Miles Parker
 */
public class ReviewsFlatLabelProvider extends ReviewsLabelProvider {

	@Override
	public boolean isStyledText(int column) {
		return column == 0 || column == 2;
	}

	@Override
	public Image getColumnImage(Object element, int column) {
		if (column == 2) {
			IFileItem file = getFile(element);
			if (file != null) {
				return super.getImage(file);
			}
		}
		return super.getColumnImage(element, column);
	}

	@Override
	protected void addTopicContainerStatsStyle(Object element, StyledString styledString) {
		//Don't include comment counts for flat style, that's visible directly.
	}

	@Override
	protected StyledString getColumnStyledText(Object element, int column) {
		if (column == 0) {
			return new StyledString(getColumnText(element, column), commentStyle);
		}
		if (column == 2) {
			IFileItem file = getFile(element);
			if (file != null) {
				return getStyledText(file);
			}
		}
		if (column == 3) {
			StyledString styledString = new StyledString();
			addTopicLineNumberStyle(element, styledString);
			return styledString;
		}
		return super.getColumnStyledText(element, column);
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			switch (columnIndex) {
			case 0:
				return getText(comment);
			case 1:
				return getText(comment.getAuthor());
			case 2:
				IFileItem file = getFile(element);
				if (file != null) {
					return getColumnText(file, columnIndex);
				}
			case 3:
				return getIndexText(comment);
			case 4:
				return getText(comment.getCreationDate());
			}
		}
		if (columnIndex == 0) {
			return getText(element);
		}
		return null;
	}

	@Override
	public int getWidth(int column) {
		switch (column) {
		case 0:
			return 500;
		case 1:
			return 100;
		case 2:
			return 300;
		case 3:
			return 75;
		case 4:
			return 120;
		}
		return 200;
	}

	@Override
	public String getLabel(int column) {
		switch (column) {
		case 0:
			return "Message";
		case 1:
			return "Author";
		case 2:
			return "File";
		case 3:
			return "Location";
		case 4:
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
		case 3:
			return SWT.RIGHT;
		}
		return SWT.LEFT;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
}
