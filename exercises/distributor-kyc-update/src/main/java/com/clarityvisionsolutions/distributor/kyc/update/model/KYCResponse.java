package com.clarityvisionsolutions.distributor.kyc.update.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * class KYCResponse: This class is used to get and update the KYC response.
 *
 * @author dnebinger
 */
public class KYCResponse {

	public KYCResponse() {
	}

	public KYCResponse(String jsonString) {
		JSONObject jsonObject = new JSONObject(jsonString);

		_object = jsonObject.optString("object");
		_id = jsonObject.optString("id");

		if (jsonObject.has("review")) {
			_review = new Review(
				jsonObject.getJSONObject(
					"review"
				).toString());
		}

		_people = new ArrayList<>();
		JSONArray peopleArray = jsonObject.optJSONArray("people");

		if (peopleArray != null) {
			for (int i = 0; i < peopleArray.length(); i++) {
				_people.add(
					new Person(
						peopleArray.getJSONObject(
							i
						).toString()));
			}
		}

		_phoneNumbers = new ArrayList<>();
		JSONArray phoneNumbersArray = jsonObject.optJSONArray("phoneNumbers");

		if (phoneNumbersArray != null) {
			for (int i = 0; i < phoneNumbersArray.length(); i++) {
				_phoneNumbers.add(phoneNumbersArray.optString(i));
			}
		}

		_profiles = new ArrayList<>();
		JSONArray profilesArray = jsonObject.optJSONArray("profiles");

		if (profilesArray != null) {
			for (int i = 0; i < profilesArray.length(); i++) {
				_profiles.add(profilesArray.optString(i));
			}
		}

		_registrations = new ArrayList<>();
		JSONArray registrationsArray = jsonObject.optJSONArray("registrations");

		if (registrationsArray != null) {
			for (int i = 0; i < registrationsArray.length(); i++) {
				_registrations.add(registrationsArray.optString(i));
			}
		}

		_orders = new ArrayList<>();
		JSONArray ordersArray = jsonObject.optJSONArray("orders");

		if (ordersArray != null) {
			for (int i = 0; i < ordersArray.length(); i++) {
				_orders.add(
					new Order(
						ordersArray.getJSONObject(
							i
						).toString()));
			}
		}

		_industryClassification = jsonObject.optString(
			"industryClassification");

		_rawResponse = jsonString;
	}

	public String getId() {
		return _id;
	}

	public String getIndustryClassification() {
		return _industryClassification;
	}

	public String getObject() {
		return _object;
	}

	public List<Order> getOrders() {
		return _orders;
	}

	public List<Person> getPeople() {
		return _people;
	}

	public List<String> getPhoneNumbers() {
		return _phoneNumbers;
	}

	public List<String> getProfiles() {
		return _profiles;
	}

	public String getRawResponse() {
		return _rawResponse;
	}

	public List<String> getRegistrations() {
		return _registrations;
	}

	public Review getReview() {
		return _review;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndustryClassification(String industryClassification) {
		_industryClassification = industryClassification;
	}

	// Getters and Setters

	public void setObject(String object) {
		_object = object;
	}

	public void setOrders(List<Order> orders) {
		_orders = orders;
	}

	public void setPeople(List<Person> people) {
		_people = people;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		_phoneNumbers = phoneNumbers;
	}

	public void setProfiles(List<String> profiles) {
		_profiles = profiles;
	}

	public void setRawResponse(String rawResponse) {
		_rawResponse = rawResponse;
	}

	public void setRegistrations(List<String> registrations) {
		_registrations = registrations;
	}

	public void setReview(Review review) {
		_review = review;
	}

	public static class KYC {

		public KYC() {
		}

		public KYC(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_object = jsonObject.optString("object");
			_providerExternalId = jsonObject.optString("providerExternalId");
			_provider = jsonObject.optString("provider");
			_decision = jsonObject.optString("decision");

			if (jsonObject.has("result")) {
				_result = new Result(
					jsonObject.getJSONObject(
						"result"
					).toString());
			}
		}

		public String getDecision() {
			return _decision;
		}

		public String getObject() {
			return _object;
		}

		public String getProvider() {
			return _provider;
		}

		public String getProviderExternalId() {
			return _providerExternalId;
		}

		public Result getResult() {
			return _result;
		}

		// Getters and Setters

		public void setDecision(String decision) {
			_decision = decision;
		}

		public void setObject(String object) {
			_object = object;
		}

		public void setProvider(String provider) {
			_provider = provider;
		}

		public void setProviderExternalId(String providerExternalId) {
			_providerExternalId = providerExternalId;
		}

		public void setResult(Result result) {
			_result = result;
		}

		private String _decision;
		private String _object;
		private String _provider;
		private String _providerExternalId;
		private Result _result;

	}

	public static class Order {

		public Order() {
		}

		public Order(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_id = jsonObject.optString("id");
			_product = jsonObject.optString("product");
			_createdAt = jsonObject.optString("createdAt");
			_updatedAt = jsonObject.optString("updatedAt");
			_completedAt = jsonObject.optString("completedAt");
			_status = jsonObject.optString("status");
		}

		public String getCompletedAt() {
			return _completedAt;
		}

		public String getCreatedAt() {
			return _createdAt;
		}

		public String getId() {
			return _id;
		}

		public String getProduct() {
			return _product;
		}

		public String getStatus() {
			return _status;
		}

		public String getUpdatedAt() {
			return _updatedAt;
		}

		// Getters and Setters

		public void setCompletedAt(String completedAt) {
			_completedAt = completedAt;
		}

		public void setCreatedAt(String createdAt) {
			_createdAt = createdAt;
		}

		public void setId(String id) {
			_id = id;
		}

		public void setProduct(String product) {
			_product = product;
		}

		public void setStatus(String status) {
			_status = status;
		}

		public void setUpdatedAt(String updatedAt) {
			_updatedAt = updatedAt;
		}

		private String _completedAt;
		private String _createdAt;
		private String _id;
		private String _product;
		private String _status;
		private String _updatedAt;

	}

	public static class Person {

		public Person() {
		}

		public Person(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_object = jsonObject.optString("object");
			_name = jsonObject.optString("name");
			_submitted = jsonObject.optBoolean("submitted");
			_businessId = jsonObject.optString("businessId");

			_sources = new ArrayList<>();
			JSONArray sourcesArray = jsonObject.optJSONArray("sources");

			if (sourcesArray != null) {
				for (int i = 0; i < sourcesArray.length(); i++) {
					_sources.add(sourcesArray.optString(i));
				}
			}

			_titles = new ArrayList<>();
			JSONArray titlesArray = jsonObject.optJSONArray("titles");

			if (titlesArray != null) {
				for (int i = 0; i < titlesArray.length(); i++) {
					_titles.add(titlesArray.optString(i));
				}
			}

			if (jsonObject.has("kyc")) {
				_kyc = new KYC(
					jsonObject.getJSONObject(
						"kyc"
					).toString());
			}
		}

		public String getBusinessId() {
			return _businessId;
		}

		public KYC getKyc() {
			return _kyc;
		}

		public String getName() {
			return _name;
		}

		public String getObject() {
			return _object;
		}

		public List<String> getSources() {
			return _sources;
		}

		public List<String> getTitles() {
			return _titles;
		}

		public boolean isSubmitted() {
			return _submitted;
		}

		// Getters and Setters

		public void setBusinessId(String businessId) {
			_businessId = businessId;
		}

		public void setKyc(KYC kyc) {
			_kyc = kyc;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setObject(String object) {
			_object = object;
		}

		public void setSources(List<String> sources) {
			_sources = sources;
		}

		public void setSubmitted(boolean submitted) {
			_submitted = submitted;
		}

		public void setTitles(List<String> titles) {
			_titles = titles;
		}

		private String _businessId;
		private KYC _kyc;
		private String _name;
		private String _object;
		private List<String> _sources;
		private boolean _submitted;
		private List<String> _titles;

	}

	public static class Result {

		public Result() {
		}

		public Result(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_fields = jsonObject.optString("fields");
			_depend = jsonObject.optString("depend");
			_what = jsonObject.optString("what");
			_are = jsonObject.optString("are");
			_on = jsonObject.optString("on");
		}

		public String getAre() {
			return _are;
		}

		public String getDepend() {
			return _depend;
		}

		public String getFields() {
			return _fields;
		}

		public String getOn() {
			return _on;
		}

		public String getWhat() {
			return _what;
		}

		// Getters and Setters

		public void setAre(String are) {
			_are = are;
		}

		public void setDepend(String depend) {
			_depend = depend;
		}

		public void setFields(String fields) {
			_fields = fields;
		}

		public void setOn(String on) {
			_on = on;
		}

		public void setWhat(String what) {
			_what = what;
		}

		private String _are;
		private String _depend;
		private String _fields;
		private String _on;
		private String _what;

	}

	public static class Review {

		public Review() {
		}

		public Review(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_object = jsonObject.optString("object");
			_id = jsonObject.optString("id");
			_createdAt = jsonObject.optString("createdAt");
			_updatedAt = jsonObject.optString("updatedAt");
			_completedAt = jsonObject.optString("completedAt");

			_tasks = new ArrayList<>();
			JSONArray tasksArray = jsonObject.optJSONArray("tasks");

			if (tasksArray != null) {
				for (int i = 0; i < tasksArray.length(); i++) {
					_tasks.add(
						new Task(
							tasksArray.getJSONObject(
								i
							).toString()));
				}
			}
		}

		public String getCompletedAt() {
			return _completedAt;
		}

		public String getCreatedAt() {
			return _createdAt;
		}

		public String getId() {
			return _id;
		}

		public String getObject() {
			return _object;
		}

		public List<Task> getTasks() {
			return _tasks;
		}

		public String getUpdatedAt() {
			return _updatedAt;
		}

		// Getters and Setters

		public void setCompletedAt(String completedAt) {
			_completedAt = completedAt;
		}

		public void setCreatedAt(String createdAt) {
			_createdAt = createdAt;
		}

		public void setId(String id) {
			_id = id;
		}

		public void setObject(String object) {
			_object = object;
		}

		public void setTasks(List<Task> tasks) {
			_tasks = tasks;
		}

		public void setUpdatedAt(String updatedAt) {
			_updatedAt = updatedAt;
		}

		private String _completedAt;
		private String _createdAt;
		private String _id;
		private String _object;
		private List<Task> _tasks;
		private String _updatedAt;

	}

	public static class Task {

		public Task() {
		}

		public Task(String jsonString) {
			JSONObject jsonObject = new JSONObject(jsonString);

			_category = jsonObject.optString("category");
			_key = jsonObject.optString("key");
			_label = jsonObject.optString("label");
			_message = jsonObject.optString("message");
			_name = jsonObject.optString("name");
			_status = jsonObject.optString("status");
			_sublabel = jsonObject.optString("subLabel");

			_sources = new ArrayList<>();
			JSONArray sourcesArray = jsonObject.optJSONArray("sources");

			if (sourcesArray != null) {
				for (int i = 0; i < sourcesArray.length(); i++) {
					_sources.add(sourcesArray.optString(i));
				}
			}
		}

		public String getCategory() {
			return _category;
		}

		public String getKey() {
			return _key;
		}

		public String getLabel() {
			return _label;
		}

		public String getMessage() {
			return _message;
		}

		public String getName() {
			return _name;
		}

		public List<String> getSources() {
			return _sources;
		}

		public String getStatus() {
			return _status;
		}

		public String getSublabel() {
			return _sublabel;
		}

		// Getters and Setters

		public void setCategory(String category) {
			_category = category;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setLabel(String label) {
			_label = label;
		}

		public void setMessage(String message) {
			_message = message;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setSources(List<String> sources) {
			_sources = sources;
		}

		public void setStatus(String status) {
			_status = status;
		}

		public void setSublabel(String sublabel) {
			_sublabel = sublabel;
		}

		private String _category;
		private String _key;
		private String _label;
		private String _message;
		private String _name;
		private List<String> _sources;
		private String _status;
		private String _sublabel;

	}

	private String _id;
	private String _industryClassification;
	private String _object;
	private List<Order> _orders;
	private List<Person> _people;
	private List<String> _phoneNumbers;
	private List<String> _profiles;
	private String _rawResponse;
	private List<String> _registrations;
	private Review _review;

}