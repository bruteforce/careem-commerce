package com.careem.net;

import static org.junit.Assert.*;

import com.careem.Shippo;
import com.careem.model.ShippoTest;
import java.util.Map;
import org.junit.Test;


public class ShippoHeaderTest extends ShippoTest {

	@Test
	public void testHeaders() {
    	String apiKey = "testAPIKey";
    	String apiVersion = "testAPIVersion";
    	Shippo.setApiKey(apiKey);
    	Shippo.setApiVersion(apiVersion);
    	Map<String, String> headers = APIResource.getHeaders(null);
    	assertEquals(headers.get("Authorization"), String.format("ShippoToken %s", apiKey));
    	assertEquals(headers.get("Accept"), "application/json");
    	assertEquals(headers.get("Shippo-API-Version"), apiVersion);
    }
}