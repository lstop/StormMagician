package org.flowable.listeners;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class orgSplitStartListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9017842759311016891L;
	private Expression fileSN;

	public void setFileSN(Expression fileSN) {
		this.fileSN = fileSN;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println(delegateTask.getId());
		System.out.println(fileSN.getValue(delegateTask).toString());
	}

}
