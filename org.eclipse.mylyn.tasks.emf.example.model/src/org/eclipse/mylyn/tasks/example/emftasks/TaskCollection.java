/**
 */
package org.eclipse.mylyn.tasks.example.emftasks;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.tasks.example.emftasks.TaskCollection#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.mylyn.tasks.example.emftasks.TaskCollection#getTasks <em>Tasks</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.mylyn.tasks.example.emftasks.EmfTasksPackage#getTaskCollection()
 * @model
 * @generated
 */
public interface TaskCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.mylyn.tasks.example.emftasks.EmfTasksPackage#getTaskCollection_Label()
	 * @model required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.mylyn.tasks.example.emftasks.TaskCollection#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.mylyn.tasks.example.emftasks.SimpleTask}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tasks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tasks</em>' containment reference list.
	 * @see org.eclipse.mylyn.tasks.example.emftasks.EmfTasksPackage#getTaskCollection_Tasks()
	 * @model containment="true"
	 * @generated
	 */
	EList<SimpleTask> getTasks();

} // TaskCollection
