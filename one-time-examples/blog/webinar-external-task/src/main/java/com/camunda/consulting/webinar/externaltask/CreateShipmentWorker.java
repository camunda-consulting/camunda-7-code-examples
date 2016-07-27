package com.camunda.consulting.webinar.externaltask;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;

@WebServlet("/createShipmentWorker")
public class CreateShipmentWorker extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProcessEngine engine = BpmPlatform.getDefaultProcessEngine();
		
		resp.getOutputStream().println("Worker fetched and eexecuted External Tasks: ");
		resp.getOutputStream().println("");
		
		List<LockedExternalTask> tasks = engine.getExternalTaskService() //
				.fetchAndLock(10, "javaWorker01") //
				.topic("createShipment", 2000) //
				.execute();
		int counter = 0;
		
		for (LockedExternalTask lockedExternalTask : tasks) {
			
			resp.getOutputStream().println("Shipment created for External Task " + counter + ": " + lockedExternalTask.getId());
			
			if (counter<8) {
				engine.getExternalTaskService().complete(lockedExternalTask.getId(), "javaWorker01");
			} else if (counter==8) {
				engine.getExternalTaskService().handleBpmnError(lockedExternalTask.getId(), "javaWorker01", "errorGoodsNotAvailable");				
			} else {
				engine.getExternalTaskService().handleFailure(lockedExternalTask.getId(), "javaWorker01", "someError", 1, 1); // always (!) give it one second chance after 1 ms :-)
			}
			
			counter++;			
		}

	}

}
