package com.clarityvisionsolutions.distributor.kyc.update.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * class KYCRequest: This class is used to create a KYC request.
 *
 * @author dnebinger
 */
public class KYCRequest {

	public List<Address> getAddresses() {
		return _addresses;
	}

	public String getName() {
		return _name;
	}

	public List<Order> getOrders() {
		return _orders;
	}

	public List<Person> getPeople() {
		return _people;
	}

	public void setAddresses(List<Address> addresses) {
		_addresses = addresses;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrders(List<Order> orders) {
		_orders = orders;
	}

	public void setPeople(List<Person> people) {
		_people = people;
	}

	// Getters and Setters for KYCRequest

	/**
	 * Utility method to convert the KYCRequest object to a JSON string that we need to send to middesk.
	 */
	public String toJSON() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", _name);

		JSONArray addressesArray = new JSONArray();

		for (Address address : _addresses) {
			JSONObject addressObject = new JSONObject();

			addressObject.put(
				"address_line1", address.getAddressLine1()
			).put(
				"city", address.getCity()
			).put(
				"postal_code", address.getPostalCode()
			).put(
				"state", address.getState()
			);

			addressesArray.put(addressObject);
		}

		jsonObject.put("addresses", addressesArray);

		JSONArray peopleArray = new JSONArray();

		for (Person person : _people) {
			JSONObject personObject = new JSONObject();

			personObject.put(
				"address_line1", person.getAddressLine1()
			).put(
				"address_line2", person.getAddressLine2()
			).put(
				"city", person.getCity()
			).put(
				"device_session_id", person.getDeviceSessionId()
			).put(
				"dob", person.getDob()
			).put(
				"document_uuid", person.getDocumentUuid()
			).put(
				"email", person.getEmail()
			).put(
				"first_name", person.getFirstName()
			).put(
				"last_name", person.getLastName()
			).put(
				"phone_number", person.getPhoneNumber()
			).put(
				"postal_code", person.getPostalCode()
			).put(
				"ssn", person.getSsn()
			).put(
				"state", person.getState()
			);

			peopleArray.put(personObject);
		}

		jsonObject.put("people", peopleArray);

		JSONArray ordersArray = new JSONArray();

		for (Order order : _orders) {
			JSONObject orderObject = new JSONObject();

			orderObject.put("product", order.getProduct());

			ordersArray.put(orderObject);
		}

		jsonObject.put("orders", ordersArray);

		return jsonObject.toString();
	}

	public static class Address {

		public String getAddressLine1() {
			return _addressLine1;
		}

		public String getCity() {
			return _city;
		}

		public String getPostalCode() {
			return _postalCode;
		}

		public String getState() {
			return _state;
		}

		// Getters and Setters

		public void setAddressLine1(String addressLine1) {
			_addressLine1 = addressLine1;
		}

		public void setCity(String city) {
			_city = city;
		}

		public void setPostalCode(String postalCode) {
			_postalCode = postalCode;
		}

		public void setState(String state) {
			_state = state;
		}

		private String _addressLine1;
		private String _city;
		private String _postalCode;
		private String _state;

	}

	public static class Order {

		public String getProduct() {
			return _product;
		}

		// Getters and Setters

		public void setProduct(String product) {
			_product = product;
		}

		private String _product;

	}

	public static class Person {

		public String getAddressLine1() {
			return _addressLine1;
		}

		public String getAddressLine2() {
			return _addressLine2;
		}

		public String getCity() {
			return _city;
		}

		public String getDeviceSessionId() {
			return _deviceSessionId;
		}

		public String getDob() {
			return _dob;
		}

		public String getDocumentUuid() {
			return _documentUuid;
		}

		public String getEmail() {
			return _email;
		}

		public String getFirstName() {
			return _firstName;
		}

		public String getLastName() {
			return _lastName;
		}

		public String getPhoneNumber() {
			return _phoneNumber;
		}

		public String getPostalCode() {
			return _postalCode;
		}

		public String getSsn() {
			return _ssn;
		}

		public String getState() {
			return _state;
		}

		// Getters and Setters

		public void setAddressLine1(String addressLine1) {
			_addressLine1 = addressLine1;
		}

		public void setAddressLine2(String addressLine2) {
			_addressLine2 = addressLine2;
		}

		public void setCity(String city) {
			_city = city;
		}

		public void setDeviceSessionId(String deviceSessionId) {
			_deviceSessionId = deviceSessionId;
		}

		public void setDob(String dob) {
			_dob = dob;
		}

		public void setDocumentUuid(String documentUuid) {
			_documentUuid = documentUuid;
		}

		public void setEmail(String email) {
			_email = email;
		}

		public void setFirstName(String firstName) {
			_firstName = firstName;
		}

		public void setLastName(String lastName) {
			_lastName = lastName;
		}

		public void setPhoneNumber(String phoneNumber) {
			_phoneNumber = phoneNumber;
		}

		public void setPostalCode(String postalCode) {
			_postalCode = postalCode;
		}

		public void setSsn(String ssn) {
			_ssn = ssn;
		}

		public void setState(String state) {
			_state = state;
		}

		private String _addressLine1;
		private String _addressLine2;
		private String _city;
		private String _deviceSessionId;
		private String _dob;
		private String _documentUuid;
		private String _email;
		private String _firstName;
		private String _lastName;
		private String _phoneNumber;
		private String _postalCode;
		private String _ssn;
		private String _state;

	}

	private List<Address> _addresses;
	private String _name;
	private List<Order> _orders;
	private List<Person> _people;

}