package com.clarityvisionsolutions.distributor.kyc.update.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * class DistributorApplication: This class is used to get and update the distributor application.
 *
 * @author dnebinger
 */
public class DistributorApplication {

	public DistributorApplication(String jsonString) {
		JSONObject rootObject = new JSONObject(jsonString);

		JSONObject jsonObject = rootObject.getJSONObject(
			"objectEntryDTODistributorApplication");

		_creator = rootObject.getString("userName");

		_dateCreated = jsonObject.getLong("dateCreated");
		_dateModified = jsonObject.getLong("dateModified");
		_externalReferenceCode = jsonObject.getString("externalReferenceCode");
		_id = jsonObject.getInt("id");

		if (jsonObject.has("scopeKey")) {
			_scopeKey = jsonObject.getString("scopeKey");
		}

		_status = jsonObject.getJSONObject(
			"status"
		).getInt(
			"code"
		);

		JSONObject props = jsonObject.getJSONObject("properties");

		_applicantEmailAddress = props.getString("applicantEmailAddress");
		_applicationState = new ApplicationState(
			props.getJSONObject("applicationState"));
		_businessName = props.getString("businessName");
		_businessPhoneNumber = props.getString("businessPhoneNumber");
		_businessTaxIDNumber = props.getString("businessTaxIDNumber");
		_businessWebsiteURL = props.getString("businessWebsiteURL");

		_distributionChannels = new ArrayList<>();

		if (jsonObject.has("distributionChannels")) {
			JSONArray distributionChannelsArray = jsonObject.getJSONArray(
				"distributionChannels");

			for (int i = 0; i < distributionChannelsArray.length(); i++) {
				_distributionChannels.add(
					new DistributionChannel(
						distributionChannelsArray.getJSONObject(i)));
			}
		}

		_distributionRegions = new ArrayList<>();

		if (jsonObject.has("distributionRegions")) {
			JSONArray distributionRegionsArray = jsonObject.getJSONArray(
				"distributionRegions");

			for (int i = 0; i < distributionRegionsArray.length(); i++) {
				_distributionRegions.add(
					new DistributionRegion(
						distributionRegionsArray.getJSONObject(i)));
			}
		}

		_productsOfInterest = new ArrayList<>();

		if (jsonObject.has("productsOfInterest")) {
			JSONArray productsOfInterestArray = jsonObject.getJSONArray(
				"productsOfInterest");

			for (int i = 0; i < productsOfInterestArray.length(); i++) {
				_productsOfInterest.add(
					new ProductOfInterest(
						productsOfInterestArray.getJSONObject(i)));
			}
		}
	}

	public AnnualPurchaseVolume getAnnualPurchaseVolume() {
		return _annualPurchaseVolume;
	}

	public String getApplicantEmailAddress() {
		return _applicantEmailAddress;
	}

	public String getApplicantName() {
		return _applicantName;
	}

	public ApplicationState getApplicationState() {
		return _applicationState;
	}

	public BusinessLicense getBusinessLicense() {
		return _businessLicense;
	}

	public String getBusinessName() {
		return _businessName;
	}

	public String getBusinessPhoneNumber() {
		return _businessPhoneNumber;
	}

	public String getBusinessTaxIDNumber() {
		return _businessTaxIDNumber;
	}

	public String getBusinessWebsiteURL() {
		return _businessWebsiteURL;
	}

	public String getComments() {
		return _comments;
	}

	public String getCreator() {
		return _creator;
	}

	public long getDateCreated() {
		return _dateCreated;
	}

	public long getDateModified() {
		return _dateModified;
	}

	public List<DistributionChannel> getDistributionChannels() {
		return _distributionChannels;
	}

	public List<DistributionRegion> getDistributionRegions() {
		return _distributionRegions;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public int getId() {
		return _id;
	}

	public List<ProductOfInterest> getProductsOfInterest() {
		return _productsOfInterest;
	}

	public String getScopeKey() {
		return _scopeKey;
	}

	public int getStatus() {
		return _status;
	}

	public void setAnnualPurchaseVolume(
		AnnualPurchaseVolume annualPurchaseVolume) {

		_annualPurchaseVolume = annualPurchaseVolume;
	}

	public void setApplicantEmailAddress(String applicantEmailAddress) {
		_applicantEmailAddress = applicantEmailAddress;
	}

	public void setApplicantName(String applicantName) {
		_applicantName = applicantName;
	}

	public void setApplicationState(ApplicationState applicationState) {
		_applicationState = applicationState;
	}

	public void setBusinessLicense(BusinessLicense businessLicense) {
		_businessLicense = businessLicense;
	}

	public void setBusinessName(String businessName) {
		_businessName = businessName;
	}

	public void setBusinessPhoneNumber(String businessPhoneNumber) {
		_businessPhoneNumber = businessPhoneNumber;
	}

	public void setBusinessTaxIDNumber(String businessTaxIDNumber) {
		_businessTaxIDNumber = businessTaxIDNumber;
	}

	public void setBusinessWebsiteURL(String businessWebsiteURL) {
		_businessWebsiteURL = businessWebsiteURL;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public void setCreator(String creator) {
		_creator = creator;
	}

	public void setDateCreated(long dateCreated) {
		_dateCreated = dateCreated;
	}

	public void setDateModified(long dateModified) {
		_dateModified = dateModified;
	}

	public void setDistributionChannels(
		List<DistributionChannel> distributionChannels) {

		_distributionChannels = distributionChannels;
	}

	public void setDistributionRegions(
		List<DistributionRegion> distributionRegions) {

		_distributionRegions = distributionRegions;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setId(int id) {
		_id = id;
	}

	public void setProductsOfInterest(
		List<ProductOfInterest> productsOfInterest) {

		_productsOfInterest = productsOfInterest;
	}

	public void setScopeKey(String scopeKey) {
		_scopeKey = scopeKey;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private AnnualPurchaseVolume _annualPurchaseVolume;
	private String _applicantEmailAddress;
	private String _applicantName;
	private ApplicationState _applicationState;
	private BusinessLicense _businessLicense;
	private String _businessName;
	private String _businessPhoneNumber;
	private String _businessTaxIDNumber;
	private String _businessWebsiteURL;
	private String _comments;
	private String _creator;
	private long _dateCreated;
	private long _dateModified;
	private List<DistributionChannel> _distributionChannels;
	private List<DistributionRegion> _distributionRegions;
	private String _externalReferenceCode;
	private int _id;
	private List<ProductOfInterest> _productsOfInterest;
	private String _scopeKey;
	private int _status;

	private static class AnnualPurchaseVolume {

		public AnnualPurchaseVolume(JSONObject jsonObject) {
			_key = jsonObject.getString("key");
			_name = jsonObject.getString("name");
			_nameI18n = new NameI18n(jsonObject.getJSONObject("name_i18n"));
		}

		public String getKey() {
			return _key;
		}

		public String getName() {
			return _name;
		}

		public NameI18n getNameI18n() {
			return _nameI18n;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setNameI18n(NameI18n nameI18n) {
			_nameI18n = nameI18n;
		}

		private String _key;
		private String _name;
		private NameI18n _nameI18n;

	}

	private static class ApplicationState {

		public ApplicationState(JSONObject jsonObject) {
			_key = jsonObject.getString("key");
			_name = jsonObject.getString("name");

			if (jsonObject.has("name_i18n")) {
				_nameI18n = new NameI18n(jsonObject.getJSONObject("name_i18n"));
			}
		}

		public String getKey() {
			return _key;
		}

		public String getName() {
			return _name;
		}

		public NameI18n getNameI18n() {
			return _nameI18n;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setNameI18n(NameI18n nameI18n) {
			_nameI18n = nameI18n;
		}

		private String _key;
		private String _name;
		private NameI18n _nameI18n;

	}

	private static class BusinessLicense {

		public BusinessLicense(JSONObject jsonObject) {
			_externalReferenceCode = jsonObject.getString(
				"externalReferenceCode");
			_fileBase64 = jsonObject.getString("fileBase64");
			_folder = new Folder(jsonObject.getJSONObject("folder"));
			_id = jsonObject.getInt("id");
			_link = new Link(jsonObject.getJSONObject("link"));
			_name = jsonObject.getString("name");
			_scope = new Scope(jsonObject.getJSONObject("scope"));
		}

		public String getExternalReferenceCode() {
			return _externalReferenceCode;
		}

		public String getFileBase64() {
			return _fileBase64;
		}

		public Folder getFolder() {
			return _folder;
		}

		public int getId() {
			return _id;
		}

		public Link getLink() {
			return _link;
		}

		public String getName() {
			return _name;
		}

		public Scope getScope() {
			return _scope;
		}

		public void setExternalReferenceCode(String externalReferenceCode) {
			_externalReferenceCode = externalReferenceCode;
		}

		public void setFileBase64(String fileBase64) {
			_fileBase64 = fileBase64;
		}

		public void setFolder(Folder folder) {
			_folder = folder;
		}

		public void setId(int id) {
			_id = id;
		}

		public void setLink(Link link) {
			_link = link;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setScope(Scope scope) {
			_scope = scope;
		}

		private String _externalReferenceCode;
		private String _fileBase64;
		private Folder _folder;
		private int _id;
		private Link _link;
		private String _name;
		private Scope _scope;

	}

	private static class DistributionChannel {

		public DistributionChannel(JSONObject jsonObject) {
			_key = jsonObject.getString("key");
			_name = jsonObject.getString("name");
			_nameI18n = new NameI18n(jsonObject.getJSONObject("name_i18n"));
		}

		public String getKey() {
			return _key;
		}

		public String getName() {
			return _name;
		}

		public NameI18n getNameI18n() {
			return _nameI18n;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setNameI18n(NameI18n nameI18n) {
			_nameI18n = nameI18n;
		}

		private String _key;
		private String _name;
		private NameI18n _nameI18n;

	}

	private static class DistributionRegion {

		public DistributionRegion(JSONObject jsonObject) {
			_key = jsonObject.getString("key");
			_name = jsonObject.getString("name");
			_nameI18n = new NameI18n(jsonObject.getJSONObject("name_i18n"));
		}

		public String getKey() {
			return _key;
		}

		public String getName() {
			return _name;
		}

		public NameI18n getNameI18n() {
			return _nameI18n;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setNameI18n(NameI18n nameI18n) {
			_nameI18n = nameI18n;
		}

		private String _key;
		private String _name;
		private NameI18n _nameI18n;

	}

	private static class Folder {

		public Folder(JSONObject jsonObject) {
			_externalReferenceCode = jsonObject.getString(
				"externalReferenceCode");
			_siteId = jsonObject.getInt("siteId");
		}

		public String getExternalReferenceCode() {
			return _externalReferenceCode;
		}

		public int getSiteId() {
			return _siteId;
		}

		public void setExternalReferenceCode(String externalReferenceCode) {
			_externalReferenceCode = externalReferenceCode;
		}

		public void setSiteId(int siteId) {
			_siteId = siteId;
		}

		private String _externalReferenceCode;
		private int _siteId;

	}

	private static class Link {

		public Link(JSONObject jsonObject) {
			_href = jsonObject.getString("href");
			_label = jsonObject.getString("label");
		}

		public String getHref() {
			return _href;
		}

		public String getLabel() {
			return _label;
		}

		public void setHref(String href) {
			_href = href;
		}

		public void setLabel(String label) {
			_label = label;
		}

		private String _href;
		private String _label;

	}

	private static class NameI18n {

		public NameI18n(JSONObject jsonObject) {
			_additionalProp1 = jsonObject.getString("additionalProp1");
			_additionalProp2 = jsonObject.getString("additionalProp2");
			_additionalProp3 = jsonObject.getString("additionalProp3");
		}

		public String getAdditionalProp1() {
			return _additionalProp1;
		}

		public String getAdditionalProp2() {
			return _additionalProp2;
		}

		public String getAdditionalProp3() {
			return _additionalProp3;
		}

		public void setAdditionalProp1(String additionalProp1) {
			_additionalProp1 = additionalProp1;
		}

		public void setAdditionalProp2(String additionalProp2) {
			_additionalProp2 = additionalProp2;
		}

		public void setAdditionalProp3(String additionalProp3) {
			_additionalProp3 = additionalProp3;
		}

		private String _additionalProp1;
		private String _additionalProp2;
		private String _additionalProp3;

	}

	private static class ProductOfInterest {

		public ProductOfInterest(JSONObject jsonObject) {
			_key = jsonObject.getString("key");
			_name = jsonObject.getString("name");
			_nameI18n = new NameI18n(jsonObject.getJSONObject("name_i18n"));
		}

		public String getKey() {
			return _key;
		}

		public String getName() {
			return _name;
		}

		public NameI18n getNameI18n() {
			return _nameI18n;
		}

		public void setKey(String key) {
			_key = key;
		}

		public void setName(String name) {
			_name = name;
		}

		public void setNameI18n(NameI18n nameI18n) {
			_nameI18n = nameI18n;
		}

		private String _key;
		private String _name;
		private NameI18n _nameI18n;

	}

	private static class Scope {

		public Scope(JSONObject jsonObject) {
			_externalReferenceCode = jsonObject.getString(
				"externalReferenceCode");
			_type = jsonObject.getString("type");
		}

		public String getExternalReferenceCode() {
			return _externalReferenceCode;
		}

		public String getType() {
			return _type;
		}

		public void setExternalReferenceCode(String externalReferenceCode) {
			_externalReferenceCode = externalReferenceCode;
		}

		public void setType(String type) {
			_type = type;
		}

		private String _externalReferenceCode;
		private String _type;

	}

}