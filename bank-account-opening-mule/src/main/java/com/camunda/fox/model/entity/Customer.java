package com.camunda.fox.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	private String title;
	private Date dateofbirth;
	private String placeofbirth;
	private String gender;
	private String phonenumber;
	private String email;
	@OneToMany(cascade={CascadeType.ALL})
	private List<Address> addresses = new ArrayList<Address>();

	public Customer(String firstname, String lastname, String title,
			Date dateofbirth, String placeofbirth, String gender,
			String phonenumber, String email, List<Address> addresses) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.dateofbirth = dateofbirth;
		this.placeofbirth = placeofbirth;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.email = email;
		this.addresses.addAll(addresses);
	}

	public Customer(String firstname, String lastname, String title,
			Date dateofbirth, String placeofbirth, String gender,
			String phonenumber, String email, Address address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.dateofbirth = dateofbirth;
		this.placeofbirth = placeofbirth;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.email = email;
		this.addresses.add(address);
	}

	public Customer() {
		super();
	}

	// -------------------------------- getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getPlaceofbirth() {
		return placeofbirth;
	}

	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddresses() {
		return new ArrayList<Address>(addresses);
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public void removeAddress(Address newAddress) {
		for (int i = 0; i < this.addresses.size(); i++) {
			if (addresses.get(i).equals(newAddress)) {
				addresses.remove(i);
				break;
			}
		}
	}

	// -------------------------------- convenience methods

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", title=" + title
				+ ", dateofbirth=" + dateofbirth + ", placeofbirth="
				+ placeofbirth + ", gender=" + gender + ", phonenumber="
				+ phonenumber + ", email=" + email + ", addresses=" + addresses
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result
				+ ((dateofbirth == null) ? 0 : dateofbirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result
				+ ((phonenumber == null) ? 0 : phonenumber.hashCode());
		result = prime * result
				+ ((placeofbirth == null) ? 0 : placeofbirth.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (dateofbirth == null) {
			if (other.dateofbirth != null)
				return false;
		} else if (!dateofbirth.equals(other.dateofbirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (phonenumber == null) {
			if (other.phonenumber != null)
				return false;
		} else if (!phonenumber.equals(other.phonenumber))
			return false;
		if (placeofbirth == null) {
			if (other.placeofbirth != null)
				return false;
		} else if (!placeofbirth.equals(other.placeofbirth))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
