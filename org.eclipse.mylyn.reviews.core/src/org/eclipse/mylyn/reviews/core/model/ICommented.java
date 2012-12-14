/**
 * Copyright (c) 2012 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 */
package org.eclipse.mylyn.reviews.core.model;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Commented</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.reviews.core.model.ICommented#getAllComments <em>All Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface ICommented extends EObject {
	/**
	 * Returns the value of the '<em><b>All Comments</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.reviews.core.model.IComment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Comments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Comments</em>' reference list.
	 * @generated
	 */
	List<IComment> getAllComments();

} // ICommented
