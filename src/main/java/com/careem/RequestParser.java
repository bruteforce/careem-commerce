package com.careem;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deepak on 26/02/17.
 */
public class RequestParser {
    public static Map<String, Object> parseAddressFromRequest(Request req){
        JsonObject addressObject = new Gson().fromJson(req.body(), JsonObject.class);
        Map<String, Object> newAddress = new HashMap<String, Object>();
        newAddress.put("object_purpose", addressObject.get("object_purpose"));
        newAddress.put("name", addressObject.get("name"));
        newAddress.put("company", addressObject.get("company"));
        newAddress.put("street1",addressObject.get("street1"));
        newAddress.put("street1",addressObject.get("street1"));
        newAddress.put("city", addressObject.get("city"));
        newAddress.put("state", addressObject.get("state"));
        newAddress.put("zip", addressObject.get("zip"));
        newAddress.put("country", addressObject.get("country"));
        newAddress.put("phone", addressObject.get("phone"));
        newAddress.put("email", addressObject.get("email"));
        return newAddress;
    }

    public static Map<String,Object> parseParcelFromRequest(Request req) {
        JsonObject parcelObject = new Gson().fromJson(req.body(), JsonObject.class);
        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("length", parcelObject.get("length"));
        parcelMap.put("width", parcelObject.get("width"));
        parcelMap.put("height", parcelObject.get("height"));
        parcelMap.put("distance_unit", parcelObject.get("distance_unit"));
        parcelMap.put("weight", parcelObject.get("weight"));
        parcelMap.put("mass_unit", parcelObject.get("mass_unit"));
        return parcelMap;
    }

    public static Map<String,Object> parseShipmentFromRequest(Request req) {
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

        return shipmentMap;
    }

    public static Map<String,Object> parseTransactionFromRequest(Request req) {
        JsonObject transactionObject = new Gson().fromJson(req.body(), JsonObject.class);
        Map<String, Object> transParams = new HashMap<String, Object>();
        //transParams.put("rate", rate.getObjectId());
        transParams.put("async", false);
        return transParams;
    }


    public static Map<String,Object> parseCarrierFromRequest(Request req) {
        JsonObject carrierObject = new Gson().fromJson(req.body(), JsonObject.class);
        Map<String, Object> carrierParams = new HashMap<String, Object>();
        carrierParams.put("carrier", carrierObject.get("carrier"));
        carrierParams.put("account_id", carrierObject.get("account_id"));
        carrierParams.put("test", carrierObject.get("test"));
        return carrierParams;

    }
}
