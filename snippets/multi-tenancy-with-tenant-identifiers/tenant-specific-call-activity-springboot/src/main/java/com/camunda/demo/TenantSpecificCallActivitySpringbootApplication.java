package com.camunda.demo;

import com.camunda.consulting.util.UserGenerator;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@EnableProcessApplication
@SpringBootApplication
public class TenantSpecificCallActivitySpringbootApplication {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private IdentityService identityService;

	public static void main(String[] args) {
		SpringApplication.run(TenantSpecificCallActivitySpringbootApplication.class, args);
	}

	@Bean
	public CalledElementTenantIdProvider calledElementTenantIdProvider(){
		return new CalledElementTenantIdProvider();
	}

	@PostConstruct
    public void init() throws Exception {

        Tenant tenant1 = identityService.newTenant("acme_inc.");
        identityService.saveTenant(tenant1);

        Tenant tenant2 = identityService.newTenant("awesome_saas_ltd.");
        identityService.saveTenant(tenant2);

        UserGenerator.addUser(processEngine, "peter", "peter", "Peter", "Silie");
        UserGenerator.addUser(processEngine, "max", "max", "Max", "Mustermann");

        identityService.createTenantUserMembership("acme_inc.", "peter");
        identityService.createTenantUserMembership("awesome_saas_ltd.", "max");

        UserGenerator.addGroup(processEngine, "users", "Users", "peter", "max");

        UserGenerator.createGrantGroupAuthorization(processEngine, new String[]{"users"}, new Permission[]{Permissions.ALL}, Resources.PROCESS_DEFINITION, new String[]{"*"});
        UserGenerator.createGrantGroupAuthorization(processEngine, new String[]{"users"}, new Permission[]{Permissions.ALL}, Resources.PROCESS_INSTANCE, new String[]{"*"});



    }
}
