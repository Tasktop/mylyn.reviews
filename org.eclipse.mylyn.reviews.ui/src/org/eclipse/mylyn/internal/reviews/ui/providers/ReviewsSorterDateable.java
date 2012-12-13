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

import java.util.Date;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.mylyn.reviews.core.model.IDated;

/**
 * Sorts by raw date. (Provided for framework extenders.)
 * 
 * @see IDated
 * @author Miles Parker
 */
public class ReviewsSorterDateable extends ViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Date d1 = null;
		if (e1 instanceof IDated) {
			IDated ed1 = (IDated) e1;
			d1 = ed1.getModificationDate();
			if (d1 == null) {
				d1 = ed1.getCreationDate();
			}
		}
		Date d2 = null;
		if (e2 instanceof IDated) {
			IDated ed2 = (IDated) e2;
			d2 = ed2.getModificationDate();
			if (d2 == null) {
				d2 = ed2.getCreationDate();
			}
		}

		if (d1 != null && d2 != null) {
			return d1.compareTo(d2);
		}
		if (d1 != null) {
			return -1;
		}
		if (d2 != null) {
			return 1;
		}
		return super.compare(viewer, e1, e2);
	}

	@Override
	public int category(Object element) {
		if (element instanceof IDated) {
			return 1;
		}
		return super.category(element);
	}
}
