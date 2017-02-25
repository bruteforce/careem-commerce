package com.careem.model;

import java.util.Map;

import com.careem.Constants;
import com.careem.exception.APIConnectionException;
import com.careem.exception.APIException;
import com.careem.exception.AuthenticationException;
import com.careem.exception.InvalidRequestException;
import com.careem.exception.RequestTimeoutException;
import com.careem.net.APIResource;

public class Transaction extends APIResource {

	String objectState;
	String objectStatus;
	String object_purpose;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
	Object commercialInvoiceUrl;
	Object wasTest;
	Object rate;
	Object trackingNumber;
	Object trackingStatus;
	Object trackingUrlProvider;
	Object labelUrl;
	Object messages;
	Object customsNote;
	Object submissionNote;
	Object metadata;

	public static Transaction create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return create(params, null);
	}

	public String getInstanceURL() {
		return "";
	}

	public static Transaction create(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(Transaction.class), params,
				Transaction.class, apiKey);
	}

	public static Transaction createSync(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, RequestTimeoutException {
		return createSync(params, null);
	}

	public static Transaction createSync(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException,
			RequestTimeoutException {

		Transaction transaction = request(RequestMethod.POST,
				classURL(Transaction.class), params, Transaction.class, apiKey);
		String object_id = transaction.objectId;
		String objectStatus = transaction.objectStatus;
		long startTime = System.currentTimeMillis();

		while (objectStatus.equals("QUEUED")
				|| objectStatus.equals("WAITING")) {
			if (System.currentTimeMillis() - startTime > Constants.TRANSACTION_REQ_TIMEOUT) {
				throw new RequestTimeoutException(
						"A timeout has occured while waiting for your label to generate.");
			}
			transaction = retrieve(object_id);
			objectStatus = (String) transaction.objectStatus;
		}

		return transaction;

	}

	public static Transaction retrieve(String id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static Transaction retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Transaction.class, id),
				null, Transaction.class, apiKey);
	}

	public static TransactionCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return all(params, null);
	}

	public static TransactionCollection all(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, classURL(Transaction.class), params,
				TransactionCollection.class, apiKey);
	}

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}

	public String getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}

	public String getObject_purpose() {
		return object_purpose;
	}

	public void setObject_purpose(String object_purpose) {
		this.object_purpose = object_purpose;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectOwner() {
		return objectOwner;
	}

	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}

	public Object getObjectCreated() {
		return objectCreated;
	}

	public void setObjectCreated(Object objectCreated) {
		this.objectCreated = objectCreated;
	}

	public Object getObjectUpdated() {
		return objectUpdated;
	}

	public void setObjectUpdated(Object objectUpdated) {
		this.objectUpdated = objectUpdated;
	}

	public Object getWasTest() {
		return wasTest;
	}

	public void setWasTest(Object wasTest) {
		this.wasTest = wasTest;
	}

	public Object getRate() {
		return rate;
	}

	public void setRate(Object rate) {
		this.rate = rate;
	}

	public Object getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(Object trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Object getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(Object trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	public Object getTrackingUrlProvider() {
		return trackingUrlProvider;
	}

	public void setTrackingUrlProvider(Object trackingUrlProvider) {
		this.trackingUrlProvider = trackingUrlProvider;
	}

	public Object getLabelUrl() {
		return labelUrl;
	}

	public void setLabelUrl(Object labelUrl) {
		this.labelUrl = labelUrl;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public Object getCustomsNote() {
		return customsNote;
	}

	public void setCustomsNote(Object customsNote) {
		this.customsNote = customsNote;
	}

	public Object getSubmissionNote() {
		return submissionNote;
	}

	public void setSubmissionNote(Object submissionNote) {
		this.submissionNote = submissionNote;
	}
	
	public Object getCommercialInvoiceUrl() {
		return commercialInvoiceUrl;
	}

	public void setCommercialInvoiceUrl(Object commercialInvoiceUrl) {
		this.commercialInvoiceUrl = commercialInvoiceUrl;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}


}