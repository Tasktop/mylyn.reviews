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

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.mylyn.reviews.core.model.IComment;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;

public class NonCommentFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IComment) {
			IComment comment = (IComment) element;
			if (comment.getAuthor() != null && StringUtils.startsWith(comment.getAuthor().getDisplayName(), "Hudson")) {
				return false;
			}
			return true;
		}
		if (element instanceof ITopicContainer) {
			ITopicContainer container = (ITopicContainer) element;
			return container.getAllComments().size() > 0;
		}
		return true;
	}
}
