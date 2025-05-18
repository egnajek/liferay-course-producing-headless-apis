/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.clarityvisionsolutions.distributor.kyc.update;

import com.clarityvisionsolutions.distributor.kyc.update.model.DistributorApplication;
import com.clarityvisionsolutions.distributor.kyc.update.model.DistributorKYCVerification;
import com.clarityvisionsolutions.distributor.kyc.update.model.KYCRequest;
import com.clarityvisionsolutions.distributor.kyc.update.model.KYCResponse;

import com.liferay.client.extension.util.spring.boot3.BaseRestController;

import java.time.Instant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * class DistributorKYCUpdateController: This class is used to update the KYCResponse.KYC status of a distributor.
 *
 * @author dnebinger
 */
@RequestMapping("PLACEHOLDER")
@RestController
public class DistributorKYCUpdateController extends BaseRestController {

	PLACEHOLDER

		try {
			_log.info("Received JSON: " + json);

			// extract the details from the JSON that we
			// need to invoke middesk with

			DistributorApplication distributorApplication =
				new DistributorApplication(json);

			// get the api key and url from the application
			// properties

			// String middeskApiKey =
			//   _environment.getProperty("middesk.api.key");
			// String middeskApiUrl =
			//   _environment.getProperty("middesk.api.url");

			// POST to middesk API with the details and get the response
			// KYCResponse kycResponse =
			//   invokeMiddeskKYCService(distributorApplication);

			KYCResponse kycResponse = _mockKYCResponse(distributorApplication);

			// process the response to extract the details we need.

			// create the Distributor KYCResponse.KYC Details object with
			// the details we got from middesk

			DistributorKYCVerification distributorKYCVerification =
				_generateDistributorKYCVerification(
					kycResponse, distributorApplication);

			// save the Distributor KYCResponse.KYC Details object

			_addDistributorKYCVerificationToLiferay(distributorKYCVerification, jwt);

			// Return the original unmodified JSON and a success status

		}
		catch (Exception exception) {
			_log.error(
				"Error processing distributor KYCResponse.KYC: " +
					exception.getMessage(),
				exception);

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(json, httpStatus);
	}

	private void _addDistributorKYCVerificationToLiferay(
			DistributorKYCVerification details, Jwt jwt) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(
			Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + jwt.getTokenValue());

		String json = details.toJSON(
		).toString();

		_log.info("KYCResponse.KYC Details JSON: " + json);

		HttpEntity<String> request = new HttpEntity<>(json, headers);

		String liferayApiUrl = _getBaseURL() + "/o/c/distributorkycverifications/";
		//String liferayApiUrl = "http://localhost:8080/o/c/distributorkycverifications/";

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(
				liferayApiUrl, request, String.class);

			if (!response.getStatusCode(
				).is2xxSuccessful()) {

				throw new RuntimeException(
					"Failed to add KYC: " + response.getStatusCode());
			}
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Failed to add KYC: " + exception.getMessage(), exception);
		}
	}

	private KYCRequest _createKYCRequest(
		DistributorApplication distributorApplication) {

		KYCRequest kycRequest = new KYCRequest();

		// Set the name

		kycRequest.setName(distributorApplication.getBusinessName());

		// Set the addresses

		List<KYCRequest.Address> addresses = new ArrayList<>();

		kycRequest.setAddresses(addresses);

		// Set the people

		List<KYCRequest.Person> people = new ArrayList<>();

		KYCRequest.Person person = new KYCRequest.Person();

		person.setEmail(distributorApplication.getApplicantEmailAddress());

		people.add(person);

		kycRequest.setPeople(people);

		// Set the orders

		List<KYCRequest.Order> orders = new ArrayList<>();

		kycRequest.setOrders(orders);

		return kycRequest;
	}

	private DistributorKYCVerification _generateDistributorKYCVerification(
		KYCResponse kycResponse,
		DistributorApplication distributorApplication) {

		DistributorKYCVerification distributorKYCVerification =
			new DistributorKYCVerification();

		// Populate fields from KYCResponse

		distributorKYCVerification.setKYCResponse(kycResponse.getRawResponse());

		// Populate fields from DistributorApplication

		distributorKYCVerification.setExternalReferenceCode(
			distributorApplication.getExternalReferenceCode());
		distributorKYCVerification.setBusinessName(
			distributorApplication.getBusinessName());
		distributorKYCVerification.setSubmittedBusinessEIN(
			distributorApplication.getBusinessTaxIDNumber());
		distributorKYCVerification.setValidatedBusinessEIN(
			distributorApplication.getBusinessTaxIDNumber());
		distributorKYCVerification.setBusinessType(
			kycResponse.getIndustryClassification());
		distributorKYCVerification.setKYCStatus(
			kycResponse.getReview(
			).getTasks(
			).get(
				0
			).getStatus());
		distributorKYCVerification.setApplicationState(
			kycResponse.getReview(
			).getTasks(
			).get(
				0
			).getStatus());
		distributorKYCVerification.setExternalReferenceCode(
			kycResponse.getReview(
			).getId());

		distributorKYCVerification.setR_applicationToKYC_c_distributorApplicationId(
			distributorApplication.getId());
		distributorKYCVerification.setR_applicationToKYC_c_distributorApplicationERC(
			distributorApplication.getExternalReferenceCode());

		return distributorKYCVerification;
	}

	private String _getBaseURL() {
		return _liferayLxcDxpServerProtocol + "://" + _liferayLxcDxpDomains;
	}

	private String _getMiddeskAPIKey() {
		return _environment.getProperty("middesk.api.key");
	}

	private String _getMiddeskAPIURL() {
		return _environment.getProperty("middesk.api.url");
	}

	private KYCResponse _invokeMiddeskKYCService(
		DistributorApplication distributorApplication) {

		KYCRequest kycRequest = _createKYCRequest(distributorApplication);

		String kycRequestJSON = kycRequest.toJSON();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(
			Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + _getMiddeskAPIKey());

		HttpEntity<String> request = new HttpEntity<>(kycRequestJSON, headers);

		String middeskApiUrl = _getMiddeskAPIURL() + "/businesses?include=kyc";

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(
				middeskApiUrl, request, String.class);

			// Use parseKYCResponse to convert the response JSON to KYCResponse

			return new KYCResponse(response.getBody());
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Failed to invoke Middesk KYCResponse.KYC Service: " +
					exception.getMessage(),
				exception);
		}
	}

	/**
	 * Utility method to mock a KYCResponse.KYC response, useful in
	 * class where developer doesn't have a middesk account.
	 */
	private KYCResponse _mockKYCResponse(
		DistributorApplication distributorApplication) {

		KYCResponse kycResponse = new KYCResponse();

		// Populate basic fields

		kycResponse.setObject("business");
		kycResponse.setId(String.valueOf(distributorApplication.getId()));
		kycResponse.setIndustryClassification("unknown");

		// Create a review object

		KYCResponse.Review review = new KYCResponse.Review();

		review.setObject("review");
		review.setId(
			UUID.randomUUID(
			).toString());
		review.setCreatedAt(
			Instant.now(
			).toString());
		review.setUpdatedAt(
			Instant.now(
			).toString());
		review.setCompletedAt(
			Instant.now(
			).toString());

		// Create tasks with random KYCResponse.KYC decision

		List<KYCResponse.Task> tasks = new ArrayList<>();

		KYCResponse.Task task = new KYCResponse.Task();

		task.setCategory("kyc");
		task.setKey("kyc_decision");
		task.setLabel("KYCResponse.KYC");
		task.setName("kyc");
		task.setSources(new ArrayList<>());

		// Randomly decide KYCResponse.KYC decision

		if (Math.random() > 0.5) {
			task.setMessage("No risks were found with the associated people");
			task.setStatus("approved");
			task.setSublabel("Approved");
		}
		else {
			task.setMessage("Risks were found with some associated people");
			task.setStatus("denied");
			task.setSublabel("Denied");
		}

		tasks.add(task);

		review.setTasks(tasks);

		kycResponse.setReview(review);

		// Populate people with individual KYCResponse.KYC decisions

		List<KYCResponse.Person> people = new ArrayList<>();

		for (String keyword : new String[] {"Manny", "Moe", "Jack"}) {
			KYCResponse.Person person = new KYCResponse.Person();

			person.setObject("person");
			person.setName(keyword);
			person.setSubmitted(true);
			person.setBusinessId(
				String.valueOf(distributorApplication.getId()));

			// Assign a random KYCResponse.KYC decision to each person

			KYCResponse.KYC kyc = new KYCResponse.KYC();

			kyc.setObject("kyc_result");
			kyc.setProviderExternalId(
				UUID.randomUUID(
				).toString());
			kyc.setProvider("socure");

			if (Math.random() > 0.5) {
				kyc.setDecision("accept");
				kyc.setResult(new KYCResponse.Result());
			}
			else {
				kyc.setDecision("reject");

				KYCResponse.Result result = new KYCResponse.Result();

				result.setFields("High risk detected");

				kyc.setResult(result);
			}

			person.setKyc(kyc);

			people.add(person);
		}

		kycResponse.setPeople(people);

		// Populate orders

		List<KYCResponse.Order> orders = new ArrayList<>();

		KYCResponse.Order order = new KYCResponse.Order();

		order.setId(
			UUID.randomUUID(
			).toString());
		order.setProduct("kyc");
		order.setCreatedAt(
			Instant.now(
			).toString());
		order.setUpdatedAt(
			Instant.now(
			).toString());
		order.setCompletedAt(
			Instant.now(
			).toString());
		order.setStatus("completed");

		orders.add(order);

		kycResponse.setOrders(orders);

		// Populate empty lists

		kycResponse.setPhoneNumbers(new ArrayList<>());
		kycResponse.setProfiles(new ArrayList<>());
		kycResponse.setRegistrations(new ArrayList<>());

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"id", kycResponse.getId()
		).put(
			"industryClassification", kycResponse.getIndustryClassification()
		).put(
			"object", kycResponse.getObject()
		).put(
			"orders", new JSONArray(kycResponse.getOrders())
		).put(
			"people", new JSONArray(kycResponse.getPeople())
		).put(
			"phoneNumbers", new JSONArray(kycResponse.getPhoneNumbers())
		).put(
			"profiles", new JSONArray(kycResponse.getProfiles())
		).put(
			"registrations", new JSONArray(kycResponse.getRegistrations())
		).put(
			"review", new JSONObject(kycResponse.getReview())
		);

		kycResponse.setRawResponse(jsonObject.toString());

		return kycResponse;
	}

	private static final Logger _log = LoggerFactory.getLogger(
		DistributorKYCUpdateController.class);

	@Autowired
	private Environment _environment;

	@Value("${com.liferay.lxc.dxp.mainDomain}")
	private String _liferayLxcDxpDomains;

	@Value("${com.liferay.lxc.dxp.server.protocol}")
	private String _liferayLxcDxpServerProtocol;

}