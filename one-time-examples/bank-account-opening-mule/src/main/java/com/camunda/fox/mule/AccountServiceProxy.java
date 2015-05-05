package com.camunda.fox.mule;

import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.camunda.fox.bean.AccountService;
import com.camunda.fox.model.dto.Order;

public class AccountServiceProxy {

	private final static Logger log = Logger.getLogger(AccountServiceProxy.class.getName());
	private AccountService accountService;

	public AccountServiceProxy() throws NamingException {
		log.info("Trying to look up account service from JNDI");
		accountService = (AccountService)new InitialContext().lookup("java:module/AccountService");
		log.info("Successfully looked up account service from JNDI: " + accountService);
	}
	
	public void setUpAccout(Order order) {
		accountService.setUpAccout(order);
	}
	
}
