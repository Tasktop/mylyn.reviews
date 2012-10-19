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
package org.eclipse.mylyn.internal.tasks.emf.example.connector.ui.wizards;

import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.mylyn.commons.workbench.forms.SectionComposite;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.emf.connector.AbstractEmfConnector;
import org.eclipse.mylyn.tasks.emf.connector.client.EmfClient;
import org.eclipse.mylyn.tasks.internal.emf.example.connector.core.EmfExampleCorePlugin;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage2;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Miles Parker
 */
public class EmfExampleQueryPage extends AbstractRepositoryQueryPage2 {

	public EmfExampleQueryPage(TaskRepository repository, String pageName, IRepositoryQuery query) {
		super(pageName, repository, query);
		setDescription("Enter title and select a query type.");
		setNeedsClear(true);
		setNeedsRefresh(true);
	}

	@Override
	protected void createPageContent(SectionComposite parent) {
		Composite composite = parent.getContent();
		composite.setLayout(new GridLayout(2, false));

		ModifyListener modifyListener = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updateButtons();
			}
		};
	}

	private EmfClient getClient() {
		AbstractEmfConnector connector = EmfExampleCorePlugin.getDefault().getConnector();
		return connector.getClient(getTaskRepository());
	}

	protected void updateButtons() {

		IWizardContainer c = getContainer();
		if (c != null && c.getCurrentPage() != null) {
			c.updateButtons();
		}
	}

	@Override
	public boolean isPageComplete() {
		boolean ret = (getQueryTitle() != null && getQueryTitle().trim().length() > 0);
		return ret;
	}

	@Override
	public void applyTo(IRepositoryQuery query) {
		query.setSummary(getQueryTitle());
	}

	@Override
	protected void doRefreshControls() {
		// nothing to do, only the content assist uses the configuration
	}

	@Override
	protected boolean hasRepositoryConfiguration() {
		return getClient().getConfiguration() != null;
	}

	@Override
	protected void doClearControls() {
		restoreState(null);
	}

	@Override
	protected boolean restoreState(IRepositoryQuery query) {
		if (query != null) {
			setQueryTitle(query.getSummary());
		} else {
		}
		updateButtons();
		return true;
	}

}
