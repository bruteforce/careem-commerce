package com.careem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careem.exception.CommerceException;
import com.careem.model.Rate;
import com.careem.model.Shipment;
import com.careem.model.Transaction;

public class Example {

	public static void main(String[] args) throws CommerceException {

		Constants.setApiKey("<API-KEY>");
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("object_purpose", "PURCHASE");
		toAddressMap.put("name", "Mr A");
		toAddressMap.put("company", "Kareem");
		toAddressMap.put("street1", "361, Sunehri Bagh");
		toAddressMap.put("city", "Rohini");
		toAddressMap.put("state", "Delhi");
		toAddressMap.put("zip", "110085");
		toAddressMap.put("country", "India");
		toAddressMap.put("phone", "8884638679");
		toAddressMap.put("email", "mrhippo@gocareem.com");

		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("object_purpose", "PURCHASE");
		fromAddressMap.put("name", "Ms B");
		fromAddressMap.put("company", "");
		fromAddressMap.put("street1", "Koramangla");
		fromAddressMap.put("city", "Bangalore");
		fromAddressMap.put("state", "Karnatka");
		fromAddressMap.put("zip", "110085");
		fromAddressMap.put("country", "US");
		fromAddressMap.put("email", "careem@gocareem.com");
		fromAddressMap.put("phone", "+1 619 231 1515");
		fromAddressMap.put("metadata", "Customer ID 123456");

		// parcel
		Map<String, Object> parcelMap = new HashMap<String, Object>();
		parcelMap.put("length", "5");
		parcelMap.put("width", "5");
		parcelMap.put("height", "5");
		parcelMap.put("distance_unit", "in");
		parcelMap.put("weight", "2");
		parcelMap.put("mass_unit", "lb");

		Map<String, Object> shipmentMap = new HashMap<String, Object>();
		shipmentMap.put("address_to", toAddressMap);
		shipmentMap.put("address_from", fromAddressMap);
		shipmentMap.put("parcel", parcelMap);
		shipmentMap.put("object_purpose", "PURCHASE");
		shipmentMap.put("async", false);

		Shipment shipment = Shipment.create(shipmentMap);

		List<Rate> rates = shipment.getRatesList();
		Rate rate = rates.get(0);

		System.out.println("Getting shipping label..");
		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("rate", rate.getObjectId());
		transParams.put("async", false);
		Transaction transaction = Transaction.create(transParams);

		if (transaction.getObjectStatus().equals("SUCCESS")) {
			System.out.println(String.format("Label url : %s", transaction.getLabelUrl()));
			System.out.println(String.format("Tracking number : %s", transaction.getTrackingNumber()));
		} else {
			System.out.println(String.format("An Error has occured while generating you label. Messages : %s", transaction.getMessages()));
		}
	}
}
