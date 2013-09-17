package org.camunda.bpm.demo.invoice.en.ui;

import java.io.IOException;

import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.MessagingException;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.compat.TaskForm;
import org.camunda.bpm.engine.task.Task;

@Model
public class PutCommentController {

	private String comment = null;
	private String newAssignee;
	private boolean email = false;
	private String activityID;

	@Inject
	private Instance<Conversation> conversationInstance;

	@Inject
	private TaskForm taskForm;

	@Inject
	private Task task;

	@Inject
	private TaskService taskService;

	public String getNewAssignee() {
		return newAssignee;
	}

	public void setNewAssignee(String newAssignee) {
		this.newAssignee = newAssignee;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void submit() throws IOException {
		if (!comment.isEmpty()) addComment();
		taskForm.completeTask();
	}

	public void addComment() {

		taskService.addComment(task.getId(), task.getProcessInstanceId(),
				comment);
		this.comment = null;
	}

	public void delegate(String callbackUrl) throws IOException,
			MessagingException {

		addComment();
		taskService.setAssignee(task.getId(), this.newAssignee);

		// conversation is ended (otherwise the
		// redirect will end up in an exception)
		conversationInstance.get().end();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect(callbackUrl);

	}

}
