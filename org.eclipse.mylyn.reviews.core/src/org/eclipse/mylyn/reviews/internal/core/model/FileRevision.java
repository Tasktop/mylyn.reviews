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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.mylyn.reviews.core.model.IEnableable;
import org.eclipse.mylyn.reviews.core.model.IFileItem;
import org.eclipse.mylyn.reviews.core.model.IFileRevision;
import org.eclipse.mylyn.reviews.core.model.ILocation;
import org.eclipse.mylyn.reviews.core.model.ITopic;
import org.eclipse.mylyn.reviews.core.model.ITopicContainer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>File Revision</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getTopics <em>Topics</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getDirectTopics <em>Direct Topics</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.mylyn.reviews.internal.core.model.FileRevision#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileRevision extends ReviewItem implements IFileRevision {
	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTopics() <em>Topics</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopics()
	 * @generated
	 * @ordered
	 */
	protected EList<ITopic> topics;

	/**
	 * The cached value of the '{@link #getDirectTopics() <em>Direct Topics</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectTopics()
	 * @generated
	 * @ordered
	 */
	protected EList<ITopic> directTopics;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevision() <em>Revision</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected static final String REVISION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRevision() <em>Revision</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected String revision = REVISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected IFileItem file;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FileRevision() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReviewsPackage.Literals.FILE_REVISION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_REVISION__ENABLED, oldEnabled,
					enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ITopic> getTopics() {
		if (topics == null) {
			topics = new EObjectResolvingEList<ITopic>(ITopic.class, this, ReviewsPackage.FILE_REVISION__TOPICS);
		}
		return topics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ITopic> getDirectTopics() {
		if (directTopics == null) {
			directTopics = new EObjectWithInverseResolvingEList<ITopic>(ITopic.class, this,
					ReviewsPackage.FILE_REVISION__DIRECT_TOPICS, ReviewsPackage.TOPIC__ITEM);
		}
		return directTopics;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_REVISION__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getRevision() {
		return revision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevision(String newRevision) {
		String oldRevision = revision;
		revision = newRevision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_REVISION__REVISION, oldRevision,
					revision));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_REVISION__CONTENT, oldContent,
					content));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileItem getFile() {
		if (file != null && file.eIsProxy()) {
			InternalEObject oldFile = (InternalEObject) file;
			file = (IFileItem) eResolveProxy(oldFile);
			if (file != oldFile) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReviewsPackage.FILE_REVISION__FILE,
							oldFile, file));
			}
		}
		return file;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFileItem basicGetFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFile(IFileItem newFile) {
		IFileItem oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReviewsPackage.FILE_REVISION__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITopic createTopicComment(ILocation initalLocation, String commentText) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getDirectTopics()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			return ((InternalEList<?>) getDirectTopics()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ReviewsPackage.FILE_REVISION__ENABLED:
			return isEnabled();
		case ReviewsPackage.FILE_REVISION__TOPICS:
			return getTopics();
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			return getDirectTopics();
		case ReviewsPackage.FILE_REVISION__PATH:
			return getPath();
		case ReviewsPackage.FILE_REVISION__REVISION:
			return getRevision();
		case ReviewsPackage.FILE_REVISION__CONTENT:
			return getContent();
		case ReviewsPackage.FILE_REVISION__FILE:
			if (resolve)
				return getFile();
			return basicGetFile();
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
		case ReviewsPackage.FILE_REVISION__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__TOPICS:
			getTopics().clear();
			getTopics().addAll((Collection<? extends ITopic>) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			getDirectTopics().clear();
			getDirectTopics().addAll((Collection<? extends ITopic>) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__PATH:
			setPath((String) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__REVISION:
			setRevision((String) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__CONTENT:
			setContent((String) newValue);
			return;
		case ReviewsPackage.FILE_REVISION__FILE:
			setFile((IFileItem) newValue);
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
		case ReviewsPackage.FILE_REVISION__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case ReviewsPackage.FILE_REVISION__TOPICS:
			getTopics().clear();
			return;
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			getDirectTopics().clear();
			return;
		case ReviewsPackage.FILE_REVISION__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case ReviewsPackage.FILE_REVISION__REVISION:
			setRevision(REVISION_EDEFAULT);
			return;
		case ReviewsPackage.FILE_REVISION__CONTENT:
			setContent(CONTENT_EDEFAULT);
			return;
		case ReviewsPackage.FILE_REVISION__FILE:
			setFile((IFileItem) null);
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
		case ReviewsPackage.FILE_REVISION__ENABLED:
			return enabled != ENABLED_EDEFAULT;
		case ReviewsPackage.FILE_REVISION__TOPICS:
			return topics != null && !topics.isEmpty();
		case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
			return directTopics != null && !directTopics.isEmpty();
		case ReviewsPackage.FILE_REVISION__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
		case ReviewsPackage.FILE_REVISION__REVISION:
			return REVISION_EDEFAULT == null ? revision != null : !REVISION_EDEFAULT.equals(revision);
		case ReviewsPackage.FILE_REVISION__CONTENT:
			return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
		case ReviewsPackage.FILE_REVISION__FILE:
			return file != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IEnableable.class) {
			switch (derivedFeatureID) {
			case ReviewsPackage.FILE_REVISION__ENABLED:
				return ReviewsPackage.ENABLEABLE__ENABLED;
			default:
				return -1;
			}
		}
		if (baseClass == ITopicContainer.class) {
			switch (derivedFeatureID) {
			case ReviewsPackage.FILE_REVISION__TOPICS:
				return ReviewsPackage.TOPIC_CONTAINER__TOPICS;
			case ReviewsPackage.FILE_REVISION__DIRECT_TOPICS:
				return ReviewsPackage.TOPIC_CONTAINER__DIRECT_TOPICS;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IEnableable.class) {
			switch (baseFeatureID) {
			case ReviewsPackage.ENABLEABLE__ENABLED:
				return ReviewsPackage.FILE_REVISION__ENABLED;
			default:
				return -1;
			}
		}
		if (baseClass == ITopicContainer.class) {
			switch (baseFeatureID) {
			case ReviewsPackage.TOPIC_CONTAINER__TOPICS:
				return ReviewsPackage.FILE_REVISION__TOPICS;
			case ReviewsPackage.TOPIC_CONTAINER__DIRECT_TOPICS:
				return ReviewsPackage.FILE_REVISION__DIRECT_TOPICS;
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
		result.append(" (enabled: "); //$NON-NLS-1$
		result.append(enabled);
		result.append(", path: "); //$NON-NLS-1$
		result.append(path);
		result.append(", revision: "); //$NON-NLS-1$
		result.append(revision);
		result.append(", content: "); //$NON-NLS-1$
		result.append(content);
		result.append(')');
		return result.toString();
	}

} //FileRevision
