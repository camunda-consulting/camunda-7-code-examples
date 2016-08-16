package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model;

public class NewRegistration {
	
	private String tenantName;
	
	private PlatformUser user;
	
	private String[] allowedProcessDefinitions;

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public PlatformUser getUser() {
		return user;
	}

	public void setUser(PlatformUser user) {
		this.user = user;
	}

	public String[] getAllowedProcessDefinitions() {
		return allowedProcessDefinitions;
	}

	public void setAllowedProcessDefinitions(String[] allowedProcessDefinitions) {
		this.allowedProcessDefinitions = allowedProcessDefinitions;
	}

}
