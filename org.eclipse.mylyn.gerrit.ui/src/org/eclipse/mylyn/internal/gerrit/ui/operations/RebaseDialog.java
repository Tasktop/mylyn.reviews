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

package org.eclipse.mylyn.internal.gerrit.ui.operations;

import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.internal.gerrit.core.operations.GerritOperation;
import org.eclipse.mylyn.internal.gerrit.core.operations.RebaseRequest;
import org.eclipse.mylyn.internal.gerrit.ui.GerritUiPlugin;
import org.eclipse.mylyn.internal.tasks.ui.editors.RichTextEditor;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.google.gerrit.common.data.ChangeDetail;
import com.google.gerrit.reviewdb.PatchSet;

/**
 * @author Steffen Pingel
 */
public class RebaseDialog extends GerritOperationDialog {

	private final PatchSet patchSet;

	private RichTextEditor messageEditor;

	public RebaseDialog(Shell parentShell, ITask task, PatchSet patchSet) {
		super(parentShell, task);
		this.patchSet = patchSet;
	}

	@Override
	public GerritOperation<ChangeDetail> createOperation() {
		int patchSetId = patchSet.getId().get();
		RebaseRequest request = new RebaseRequest(task.getTaskId(), patchSetId);
		return GerritUiPlugin.getDefault().getOperationFactory().createRebaseOperation(task, request);
	}

	@Override
	protected Control createPageControls(final Composite parent) {
		setTitle("Rebase Change");
		setMessage("");

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Rebase Change?");

		parent.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (!parent.isDisposed()) {
					setReturnCode(Window.OK);
					close();
				}
			}
		});

		return composite;
	}

}
