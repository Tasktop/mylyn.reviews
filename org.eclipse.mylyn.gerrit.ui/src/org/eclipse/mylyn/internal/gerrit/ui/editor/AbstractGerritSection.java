/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *     Sascha Scholz (SAP) - improvements
 *******************************************************************************/

package org.eclipse.mylyn.internal.gerrit.ui.editor;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.internal.gerrit.core.GerritCorePlugin;
import org.eclipse.mylyn.internal.gerrit.ui.operations.GerritOperationDialog;
import org.eclipse.mylyn.internal.tasks.ui.actions.SynchronizeEditorAction;
import org.eclipse.mylyn.internal.tasks.ui.editors.AbstractTaskEditorSection;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.google.gerrit.common.data.GerritConfig;

/**
 * @author Steffen Pingel
 * @author Sascha Scholz
 */
public abstract class AbstractGerritSection extends AbstractTaskEditorSection {

	public Label addTextClient(final FormToolkit toolkit, final Section section, String text) {
		return addTextClient(toolkit, section, text, true);
	}

	public Label addTextClient(final FormToolkit toolkit, final Section section, String text, boolean hideOnExpand) {
		final Label label = new Label(section, SWT.NONE);
		label.setText("  " + text); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		section.setTextClient(label);

		if (hideOnExpand) {
			label.setVisible(!section.isExpanded());
			section.addExpansionListener(new ExpansionAdapter() {
				@Override
				public void expansionStateChanged(ExpansionEvent e) {
					label.setVisible(!section.isExpanded());
				}
			});
		}

		return label;
	}

	protected Shell getShell() {
		return getTaskEditorPage().getSite().getShell();
	}

	protected ITask getTask() {
		return getTaskEditorPage().getTask();
	}

	protected void openOperationDialog(GerritOperationDialog dialog) {
		if (dialog.open() == Window.OK) {
			SynchronizeEditorAction action = new SynchronizeEditorAction();
			action.selectionChanged(new StructuredSelection(getTaskEditorPage().getEditor()));
			action.run();
		}
	}

	protected GerritConfig getConfig() {
		return GerritCorePlugin.getGerritClient(getTaskEditorPage().getTaskRepository()).getGerritConfig();
	}

}
