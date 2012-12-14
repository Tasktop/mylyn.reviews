/**
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 */
package org.eclipse.mylyn.reviews.internal.core.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.mylyn.reviews.core.model.IComment;
import org.eclipse.mylyn.reviews.core.model.IEnableable;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.IFileRevision;
import org.eclipse.mylyn.reviews.core.model.IReview;
import org.eclipse.mylyn.reviews.core.model.IReviewItem;
import org.eclipse.mylyn.reviews.core.model.IUser;
import org.eclipse.mylyn.reviews.core.model.ILocation;
import org.eclipse.mylyn.reviews.core.model.ITopic;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>File Item</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getAddedBy <em>Added By</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getReview <em>Review</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getBase <em>Base</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileItem#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileItem extends TopicContainer implements IFileItem {
	/**
	 * The cached value of the '{@link #getAddedBy() <em>Added By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddedBy()
	 * @generated
	 * @ordered
	 */
	protected IUser addedBy;

	/**
	 * The cached value of the '{@link #getReview() <em>Review</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReview()
	 * @generated
	 * @ordered
	 */
	protected IReview review;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected IFileRevision base;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected IFileRevision target;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FileItem() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReviewsPackage.Literals.FILE_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IUser getAddedBy() {
		if (addedBy != null && addedBy.eIsProxy()) {
			InternalEObject oldAddedBy = (InternalEObject) addedBy;
			addedBy = (IUser) eResolveProxy(oldAddedBy);
			if (addedBy != oldAddedBy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReviewsPackage.FILE_ITEM__ADDED_BY,
							oldAddedBy, addedBy));
			}
		}
		return addedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IUser basicGetAddedBy() {
		return addedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddedBy(IUser newAddedBy) {
		IUser oldAddedBy = addedBy;
		addedBy = newAddedBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__ADDED_BY, oldAddedBy,
					addedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IReview getReview() {
		if (review != null && review.eIsProxy()) {
			InternalEObject oldReview = (InternalEObject) review;
			review = (IReview) eResolveProxy(oldReview);
			if (review != oldReview) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReviewsPackage.FILE_ITEM__REVIEW,
							oldReview, review));
			}
		}
		return review;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IReview basicGetReview() {
		return review;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReview(IReview newReview) {
		IReview oldReview = review;
		review = newReview;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__REVIEW, oldReview, review));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileRevision getBase() {
		if (base != null && base.eIsProxy()) {
			InternalEObject oldBase = (InternalEObject) base;
			base = (IFileRevision) eResolveProxy(oldBase);
			if (base != oldBase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReviewsPackage.FILE_ITEM__BASE, oldBase,
							base));
			}
		}
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileRevision basicGetBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc --> Unmodifiable and not updated. <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public List<IComment> getAllComments() {
		BasicEList<IComment> all = new BasicEList<IComment>(getTopics());
		if (getBase() != null) {
			all.addAll(getBase().getTopics());
		}
		if (getTarget() != null) {
			all.addAll(getTarget().getTopics());
		}
		return all;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(IFileRevision newBase) {
		IFileRevision oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileRevision getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject) target;
			target = (IFileRevision) eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReviewsPackage.FILE_ITEM__TARGET,
							oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileRevision basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(IFileRevision newTarget) {
		IFileRevision oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_ITEM__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ReviewsPackage.FILE_ITEM__ADDED_BY:
			if (resolve)
				return getAddedBy();
			return basicGetAddedBy();
		case ReviewsPackage.FILE_ITEM__REVIEW:
			if (resolve)
				return getReview();
			return basicGetReview();
		case ReviewsPackage.FILE_ITEM__NAME:
			return getName();
		case ReviewsPackage.FILE_ITEM__ID:
			return getId();
		case ReviewsPackage.FILE_ITEM__BASE:
			if (resolve)
				return getBase();
			return basicGetBase();
		case ReviewsPackage.FILE_ITEM__TARGET:
			if (resolve)
				return getTarget();
			return basicGetTarget();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ReviewsPackage.FILE_ITEM__ADDED_BY:
			setAddedBy((IUser) newValue);
			return;
		case ReviewsPackage.FILE_ITEM__REVIEW:
			setReview((IReview) newValue);
			return;
		case ReviewsPackage.FILE_ITEM__NAME:
			setName((String) newValue);
			return;
		case ReviewsPackage.FILE_ITEM__ID:
			setId((String) newValue);
			return;
		case ReviewsPackage.FILE_ITEM__BASE:
			setBase((IFileRevision) newValue);
			return;
		case ReviewsPackage.FILE_ITEM__TARGET:
			setTarget((IFileRevision) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ReviewsPackage.FILE_ITEM__ADDED_BY:
			setAddedBy((IUser) null);
			return;
		case ReviewsPackage.FILE_ITEM__REVIEW:
			setReview((IReview) null);
			return;
		case ReviewsPackage.FILE_ITEM__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ReviewsPackage.FILE_ITEM__ID:
			setId(ID_EDEFAULT);
			return;
		case ReviewsPackage.FILE_ITEM__BASE:
			setBase((IFileRevision) null);
			return;
		case ReviewsPackage.FILE_ITEM__TARGET:
			setTarget((IFileRevision) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ReviewsPackage.FILE_ITEM__ADDED_BY:
			return addedBy != null;
		case ReviewsPackage.FILE_ITEM__REVIEW:
			return review != null;
		case ReviewsPackage.FILE_ITEM__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ReviewsPackage.FILE_ITEM__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case ReviewsPackage.FILE_ITEM__BASE:
			return base != null;
		case ReviewsPackage.FILE_ITEM__TARGET:
			return target != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IReviewItem.class) {
			switch (derivedFeatureID) {
			case ReviewsPackage.FILE_ITEM__ADDED_BY:
				return ReviewsPackage.REVIEW_ITEM__ADDED_BY;
			case ReviewsPackage.FILE_ITEM__REVIEW:
				return ReviewsPackage.REVIEW_ITEM__REVIEW;
			case ReviewsPackage.FILE_ITEM__NAME:
				return ReviewsPackage.REVIEW_ITEM__NAME;
			case ReviewsPackage.FILE_ITEM__ID:
				return ReviewsPackage.REVIEW_ITEM__ID;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IReviewItem.class) {
			switch (baseFeatureID) {
			case ReviewsPackage.REVIEW_ITEM__ADDED_BY:
				return ReviewsPackage.FILE_ITEM__ADDED_BY;
			case ReviewsPackage.REVIEW_ITEM__REVIEW:
				return ReviewsPackage.FILE_ITEM__REVIEW;
			case ReviewsPackage.REVIEW_ITEM__NAME:
				return ReviewsPackage.FILE_ITEM__NAME;
			case ReviewsPackage.REVIEW_ITEM__ID:
				return ReviewsPackage.FILE_ITEM__ID;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //FileItem
