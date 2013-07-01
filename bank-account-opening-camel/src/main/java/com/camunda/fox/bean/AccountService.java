package com.camunda.fox.bean;

import java.util.UUID;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camunda.fox.model.dto.Order;
import com.camunda.fox.model.entity.Account;
import com.camunda.fox.model.entity.Address;
import com.camunda.fox.model.entity.Customer;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
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
