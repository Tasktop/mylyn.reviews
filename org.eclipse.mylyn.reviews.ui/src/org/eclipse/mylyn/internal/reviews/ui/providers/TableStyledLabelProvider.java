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

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * Supports independent styling of individual table columns.
 * 
 * @author Miles Parker
 */
public abstract class TableStyledLabelProvider extends LabelProvider implements IStyledLabelProvider,
		ITableLabelProvider {

	public boolean isSortUp(int column) {
		return true;
	}

	public int getSwtStyle(int column) {
		return SWT.LEFT;
	}

	protected StyledString getColumnStyledText(Object element, int index) {
		String columnText = getColumnText(element, index);
		if (columnText == null) {
			columnText = "";
		}
		return new StyledString(columnText);
	}

	public boolean isStyledText(int column) {
		return false;
	}

	public Image getColumnImage(Object element, int column) {
		if (column == 0) {
			return getImage(element);
		}
		return null;
	}

	public String getColumnToolTipText(Object element, int column) {
		return getColumnText(element, column);
	}

	public void createTable(TreeViewer viewer, Tree treeTable) {
		ColumnViewerToolTipSupport.enableFor(viewer);
		for (int i = 0; i < getColumnCount(); i++) {
			createColumn(viewer, treeTable, i);
		}
	}

	public void createColumn(TreeViewer viewer, Tree treeTable, final int index) {
		TreeColumn column = null;
		int styleBits = getSwtStyle(index);
		if (isStyledText(index)) {
			TreeViewerColumn viewerColumn = new TreeViewerColumn(viewer, styleBits);
			final DelegatingStyledCellLabelProvider styledLabelProvider = new DelegatingStyledCellLabelProvider(this) {
				@Override
				protected StyledString getStyledText(Object element) {
					return getColumnStyledText(element, index);
				}

				@Override
				public Image getImage(Object element) {
					return getColumnImage(element, index);
				}

				@Override
				public String getToolTipText(Object element) {
					return getColumnToolTipText(element, index);
				}

			};
			viewerColumn.setLabelProvider(styledLabelProvider);
			column = viewerColumn.getColumn();
		} else {
			column = new TreeColumn(treeTable, styleBits);
		}
		column.setText(getLabel(index));
		column.setWidth(getWidth(index));
	}

	public abstract int getColumnCount();

	/**
	 * Noop. Note: Subclasses must manage disposal of any member resources by overriding {@link #doDispose()}. Viewers
	 * are expected to call {@link #doDispose()} from viewer. This is necessary because internal delegating providers
	 * and viewers will call dispose, preventing reuse of this label provider.
	 */
	@Override
	public final void dispose() {
	}

	/**
	 * Override to manage resource disposal.
	 */
	public void doDispose() {
		super.dispose();
	}

	public abstract String getLabel(int column);

	public abstract int getWidth(int column);

}
