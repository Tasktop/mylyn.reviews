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

package org.eclipse.mylyn.internal.gerrit.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.internal.gerrit.core.client.GerritPatchSetContent;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;

import com.google.gerrit.reviewdb.PatchSet;
import com.google.gerrit.reviewdb.PatchSet.Id;

/**
 * @author Steffen Pingel
 */
public class ReviewItemCache {

	private final Map<String, IReviewItem> reviewItemById;

	private final Map<Id, GerritPatchSetContent> contentById = new HashMap<PatchSet.Id, GerritPatchSetContent>();

	public ReviewItemCache() {
		reviewItemById = new HashMap<String, IReviewItem>();
	}

	public IReviewItem getItem(String id) {
		return reviewItemById.get(id);
	}

	public void put(IReviewItem item) {
		reviewItemById.put(item.getId(), item);
	}

	public void putContent(Id key, GerritPatchSetContent content) {
		contentById.put(key, content);
	}

	public GerritPatchSetContent getContent(Id key) {
		return contentById.get(key);
	}

}
