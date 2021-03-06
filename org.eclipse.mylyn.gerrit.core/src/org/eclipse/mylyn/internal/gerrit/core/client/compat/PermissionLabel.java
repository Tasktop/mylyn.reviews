/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.gerrit.core.client.compat;

import com.google.gerrit.reviewdb.ApprovalCategory;
import com.google.gerrit.reviewdb.ApprovalCategoryValue;

/**
 * Manages permissions for Gerrit 2.2 and later.
 * 
 * @author Steffen Pingel
 */
public class PermissionLabel {

	protected int max;

	protected int min;

	protected String name;

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public String getName() {
		return name;
	}

	public String toLabelName(String identifier) {
		return "label-" + identifier.replace(" ", "-"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}

	public boolean matches(ApprovalCategoryValue value) {
		return value.getValue() >= min && value.getValue() <= max;
	}

	public boolean matches(ApprovalCategory approvalCategory) {
		return toLabelName(approvalCategory.getName()).equals(getName());
	}

}