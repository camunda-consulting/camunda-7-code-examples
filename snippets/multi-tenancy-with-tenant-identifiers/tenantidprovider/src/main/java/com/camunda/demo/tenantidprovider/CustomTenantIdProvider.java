package com.camunda.demo.tenantidprovider;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.impl.cfg.multitenancy.TenantIdProvider;
import org.camunda.bpm.engine.impl.cfg.multitenancy.TenantIdProviderCaseInstanceContext;
import org.camunda.bpm.engine.impl.cfg.multitenancy.TenantIdProviderHistoricDecisionInstanceContext;
import org.camunda.bpm.engine.impl.cfg.multitenancy.TenantIdProviderProcessInstanceContext;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.identity.Authentication;

public class CustomTenantIdProvider implements TenantIdProvider {

	  @Override
	  public String provideTenantIdForProcessInstance(TenantIdProviderProcessInstanceContext ctx) {
	    return getTenantIdOfCurrentAuthentication();
	  }

	  @Override
	  public String provideTenantIdForCaseInstance(TenantIdProviderCaseInstanceContext ctx) {
	    return getTenantIdOfCurrentAuthentication();
	  }

	  @Override
	  public String provideTenantIdForHistoricDecisionInstance(TenantIdProviderHistoricDecisionInstanceContext ctx) {
	    return getTenantIdOfCurrentAuthentication();
	  }

	  protected String getTenantIdOfCurrentAuthentication() {

	    IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
	    Authentication currentAuthentication = identityService.getCurrentAuthentication();

	    if (currentAuthentication != null) {

	      List<String> tenantIds = currentAuthentication.getTenantIds();
	      if (tenantIds.size() == 1) {
	        return tenantIds.get(0);

	      } else if (tenantIds.isEmpty()) {
	    	
	    	  return "";
	    	  
	        //throw new IllegalStateException("no authenticated tenant");

	      } else {
	        throw new IllegalStateException("more than one authenticated tenant");
	      }

	    } else {
	    	
	    	return "";
	      //throw new IllegalStateException("no authentication");
	    }
	  }

	}