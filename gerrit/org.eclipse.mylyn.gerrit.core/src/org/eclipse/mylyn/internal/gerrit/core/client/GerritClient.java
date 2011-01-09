/*********************************************************************
 * Copyright (c) 2010 Sony Ericsson/ST Ericsson and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Sony Ericsson/ST Ericsson - initial API and implementation
 *********************************************************************/
package org.eclipse.mylyn.internal.gerrit.core.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.commons.core.CoreUtil;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.internal.gerrit.core.GerritConnector;
import org.eclipse.mylyn.internal.gerrit.core.GerritTask;
import org.eclipse.mylyn.internal.gerrit.core.client.service.GerritServiceFactory;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.google.gerrit.common.data.AccountDashboardInfo;
import com.google.gerrit.common.data.AccountService;
import com.google.gerrit.common.data.ChangeDetail;
import com.google.gerrit.common.data.ChangeDetailService;
import com.google.gerrit.common.data.ChangeInfo;
import com.google.gerrit.common.data.ChangeListService;
import com.google.gerrit.common.data.PatchSetDetail;
import com.google.gerrit.common.data.SingleListChangeInfo;
import com.google.gerrit.reviewdb.Account;
import com.google.gerrit.reviewdb.Change;
import com.google.gerrit.reviewdb.Change.Id;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Facade to the Gerrit RPC API.
 * 
 * @author Mikael Kober
 * @author Thomas Westling
 */
public class GerritClient {
	private List<GerritTask> queryResult = null;

	private GerritTask changeResult = null;

	private ChangeListService changeListService;

	private ChangeDetailService changeDetailService = null;

	private AccountService accountService = null;

	GerritHttpClient client = null;

	private GerritConnector updateListener;

	static GerritClient gerritClient = null;

	private boolean getChangeDetailDone;

	private com.google.gerrit.reviewdb.Account.Id myid = null;

	private boolean gotId = false;

	private abstract class GerritOperation<T> implements AsyncCallback<T> {

		private T result;

		private Throwable exception;

		public T getResult() {
			return result;
		}

		public abstract void execute(IProgressMonitor monitor) throws GerritException;

		protected void setResult(T result) {
			this.result = result;
		}

		public void onSuccess(T result) {
			setResult(result);
		}

		public void onFailure(Throwable exception) {
			this.exception = exception;
		}

		public Throwable getException() {
			return exception;
		}
	}

	public static GerritClient getGerritClient(TaskRepository repository) {
		if (gerritClient != null) {
			return gerritClient;
		} else {
			return new GerritClient(repository);
		}
	}

	public GerritClient(TaskRepository repository) {
		//        String url = repository.getProperty("url");
		//        int i = url.indexOf(":");
		//        String schema = url.substring(0, i);
		//        String host = url.substring(i + 3);
		//        client = new KerberosGerritHttpClient(schema, host, GerritConstants.HTTPSPORT);
		try {
			URL url = new URL(repository.getRepositoryUrl());
			String host = url.getHost();
			String schema = url.getProtocol();
			AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
			String password = (credentials != null) ? credentials.getPassword() : null;
			client = new GerritHttpClient(schema, host, url.getPath(), url.getPort() == -1 ? url.getDefaultPort()
					: url.getPort(), repository.getUserName(), password);

			GerritServiceFactory factory = new GerritServiceFactory(client);
			changeListService = factory.getChangeListService();
			changeDetailService = factory.getChangeDetailService();
			accountService = factory.getAccountService();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected <T> T execute(IProgressMonitor monitor, GerritOperation<T> operation) throws GerritException {
		operation.execute(monitor);
		if (operation.getException() != null) {
			GerritException e = new GerritException();
			e.initCause(operation.getException());
			throw e;
		}
		return operation.getResult();
	}

	/**
	 * Gets the task data for a specific task id.
	 * 
	 * @param repository
	 *            The Taskrepository in which to look for the task
	 * @param taskId
	 *            The id of the task in question
	 * @param monitor
	 *            the progress monitor
	 * @return the GerritTask we looked for, or null if none was found
	 */
	public GerritTask getTaskData(TaskRepository repository, String taskId, IProgressMonitor monitor) {
		AsyncCallback<ChangeDetail> async = new AsyncCallback<ChangeDetail>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				changeResult = null;
			}

			public void onSuccess(ChangeDetail changeDetail) {
				Change change = changeDetail.getChange();
				changeDetail.getCurrentPatchSetDetail().getInfo().getMessage();
				changeResult = new GerritTask("" + change.getChangeId(), change.getSubject());
				changeResult.setOwner(change.getOwner().toString());
				changeResult.setBranch(change.getDest().get());
				changeResult.setProject(change.getProject().get());
				changeResult.setStatus(change.getStatus().toString());
				changeResult.setUploaded(change.getCreatedOn());
				changeResult.setUpdated(change.getLastUpdatedOn());
				changeResult.setChangeId(change.getKey().get());
				changeResult.setDescription(changeDetail.getDescription());
				getChangeDetailDone = true;
			}
		};
		Id id = new Id(Integer.parseInt(taskId));
		changeDetailService.changeDetail(id, async);
		while (!getChangeDetailDone) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getChangeDetailDone = false;
		return changeResult;
	}

	/**
	 * Called to get the latest 25 tasks from gerrit
	 * 
	 * @param repository
	 *            The TaskRepository associated with the query
	 * @param monitor
	 *            The progress monitor
	 * @throws GerritException
	 */
	public void allQuery(final TaskRepository repository, final IProgressMonitor monitor) throws GerritException {
		SingleListChangeInfo sl = execute(monitor, new GerritOperation<SingleListChangeInfo>() {
			@Override
			public void execute(IProgressMonitor monitor) throws GerritException {
				changeListService.allQueryNext("status:open", "z", 25, this);
			}
		});

		queryResult = new ArrayList<GerritTask>();
		List<GerritTask> result = new ArrayList<GerritTask>();
		List<ChangeInfo> changes = sl.getChanges();
		for (final ChangeInfo changeInfo : changes) {
			GerritTask task = new GerritTask(changeInfo.getId().toString(), changeInfo.getSubject());
			task.setBranch(changeInfo.getBranch());
			task.setProject(changeInfo.getProject().getName());
			task.setOwner(changeInfo.getOwner().toString());
			task.setStatus(changeInfo.getStatus().toString());
			task.setUpdated(changeInfo.getLastUpdatedOn());
			task.setChangeId(changeInfo.getKey().get());
			result.add(task);

			final ChangeDetail detail = execute(monitor, new GerritOperation<ChangeDetail>() {
				@Override
				public void execute(IProgressMonitor monitor) throws GerritException {
					changeDetailService.changeDetail(changeInfo.getId(), this);
				}
			});
			PatchSetDetail patchSet = execute(monitor, new GerritOperation<PatchSetDetail>() {
				@Override
				public void execute(IProgressMonitor monitor) throws GerritException {
					changeDetailService.patchSetDetail(detail.getPatchSets().iterator().next().getId(), this);
				}
			});
			System.err.println(CoreUtil.toString(detail));
			System.err.println(CoreUtil.toString(patchSet));
		}
		updateListener.updateTaskRepositoryAsync(repository, result, monitor);
	}

	/**
	 * Called to get all gerrit tasks associated with the id of the user. This includes all open, closed and reviewable
	 * gerrit tasks for the user.
	 * 
	 * @param repository
	 *            The TaskRepository associated with the query
	 * @param monitor
	 *            The progress monitor
	 */
	public void myQuery(final TaskRepository repository, final IProgressMonitor monitor) {
		AsyncCallback<AccountDashboardInfo> a = new AsyncCallback<AccountDashboardInfo>() {
			public void onSuccess(AccountDashboardInfo ad) {
				List<ChangeInfo> allMyChanges = ad.getByOwner();
				allMyChanges.addAll(ad.getForReview());
				allMyChanges.addAll(ad.getClosed());
				List<GerritTask> result = new ArrayList<GerritTask>();
				for (ChangeInfo changeInfo : allMyChanges) {
					GerritTask task = new GerritTask(changeInfo.getId().toString(), changeInfo.getSubject());
					task.setBranch(changeInfo.getBranch());
					task.setProject(changeInfo.getProject().getName());
					task.setOwner(changeInfo.getOwner().toString());
					task.setStatus(changeInfo.getStatus().toString());
					task.setUpdated(changeInfo.getLastUpdatedOn());
					task.setChangeId(changeInfo.getKey().get());
					result.add(task);
				}
				updateListener.updateTaskRepositoryAsync(repository, result, monitor);
			}

			public void onFailure(Throwable caught) {
				System.out.println("Failure");
				caught.printStackTrace();
			}
		};
		queryResult = new ArrayList<GerritTask>();
		updateId();
		while (!gotId) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		changeListService.forAccount(myid, a);
	}

	/**
	 * Updates the gerrit id of the user, used in the MyQuery
	 */
	private void updateId() {
		// TODO Auto-generated method stub
		if (!gotId) {
			accountService.myAccount(new AsyncCallback<Account>() {

				public void onSuccess(Account result) {
					myid = result.getId();
					gotId = true;
				}

				public void onFailure(Throwable caught) {
					myid = null;
					gotId = true;
				}
			});
		}
	}

	/**
	 * adds an update listener, used for the different async callbacks
	 * 
	 * @param gerritConnector
	 *            the update listener
	 */
	public void addUpdateListener(GerritConnector gerritConnector) {
		updateListener = gerritConnector;

	}

}