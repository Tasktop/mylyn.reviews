/*******************************************************************************
 * Copyright (c) 2012 Ericsson
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Description:
 * 
 * Contributors:
 *   Miles Parker, Tasktop Technologies - Initial API and Implementation
 *******************************************************************************/
package org.eclipse.mylyn.internal.tasks.emf.example.connector.ui;

import org.eclipse.mylyn.tasks.emf.connector.AbstractEmfConnector;
import org.eclipse.mylyn.tasks.emf.connector.ui.EmfUrlHandler;
import org.eclipse.mylyn.tasks.internal.emf.example.connector.core.EmfExampleCorePlugin;

/**
 * @author Miles Parker
 */
public class EmfExampleUrlHandler extends EmfUrlHandler {

	@Override
	protected AbstractEmfConnector getEmfConnector() {
		return EmfExampleCorePlugin.getDefault().getConnector();
	}
}
