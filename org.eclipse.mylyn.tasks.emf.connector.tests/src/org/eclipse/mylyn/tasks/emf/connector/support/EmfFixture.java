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

package org.eclipse.mylyn.tasks.emf.connector.support;

import org.eclipse.mylyn.tasks.internal.emf.example.connector.core.EmfExampleConnector;
import org.eclipse.mylyn.tests.util.TestFixture;

/**
 * @author Miles Parker
 */
public class EmfFixture extends TestFixture {

	public static EmfFixture EMF_TEST = new EmfFixture(EmfTestConnector.CONNECTOR_KIND, "bogus:/uri", "1.0.0", ""); //$NON-NLS-1$ //$NON-NLS-2$ 

	public static EmfFixture EMF_TASKS_TEST = new EmfFixture(EmfExampleConnector.CONNECTOR_KIND,
			"bogus:/uri", "1.0.0", ""); //$NON-NLS-1$ //$NON-NLS-2$ 

	public EmfFixture(String kind, String url, String version, String description) {
		super(kind, url);
		setInfo(url, version, description);
	}

	@Override
	protected EmfFixture activate() {
		setUpFramework();
		return this;
	}

	@Override
	protected EmfFixture getDefault() {
		return EMF_TEST;
	}

	public boolean canAuthenticate() {
		return true;
	}
}
