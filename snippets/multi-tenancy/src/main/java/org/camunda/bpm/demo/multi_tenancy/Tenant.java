package org.camunda.bpm.demo.multi_tenancy;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Tenant {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
