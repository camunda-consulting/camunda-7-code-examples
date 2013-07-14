package org.camunda.demo.camel.business;

import java.util.UUID;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.demo.camel.business.entity.Account;
import org.camunda.demo.camel.business.entity.Address;
import org.camunda.demo.camel.business.entity.Customer;
import org.camunda.demo.camel.dto.Order;

@Named
public class AccountService {

	@PersistenceContext
	private EntityManager entityManager;

	public void setUpAccout(Order order) {
		Account account = new Account(createAccountNumber(),
				order.getAccounttype(), null);
		entityManager.persist(account);

		Address address = new Address(order.getAddress().getStreet(), order
				.getAddress().getNumber(), order.getAddress().getZipcode(),
				order.getAddress().getCity(), order.getAddress().getState(),
				order.getAddress().getCountry());
		Customer customer = new Customer(order.getPerson().getFirstname(),
				order.getPerson().getLastname(), order.getPerson().getTitle(),
				order.getPerson().getDateofbirth(), order.getPerson()
						.getPlaceofbirth(), order.getPerson().getGender(),
				order.getPerson().getPhonenumber(), order.getPerson()
						.getEmail(), address);

		account.setCustomer(customer);
	}

	private String createAccountNumber() {
		return UUID.randomUUID().toString();
	}

}
