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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.mylyn.reviews.core.model.IIndexed;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;

/**
 * Sorts by orderable index. (Provided for framework extenders.)
 * 
 * @see IIndexed
 * @author Miles Parker
 */
public class ReviewsSorterOrderable extends ViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof IIndexed && e2 instanceof IIndexed) {
			return IIndexed.COMPARATOR.compare((IIndexed) e1, (IIndexed) e2);
		}
		if (e1 instanceof IIndexed) {
			return -1;
		}
		if (e2 instanceof IIndexed) {
			return 1;
		}
		//We want to use full name, not shortened name in UI.
		if (e1 instanceof IReviewItem && e2 instanceof IReviewItem) {
			return super.compare(viewer, ((IReviewItem) e1).getName(), ((IReviewItem) e2).getName());
		}
		return super.compare(viewer, e1, e2);
	}

	@Override
	public int category(Object element) {
		if (element instanceof IIndexed) {
			return 1;
		}
		return super.category(element);
	}
}
