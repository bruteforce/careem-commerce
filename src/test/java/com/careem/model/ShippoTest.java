package com.careem.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import com.careem.Shippo;

public class ShippoTest {
	static boolean VERBOSE_TESTING = true;

	@Before
	public void setAPIKey() {
		Shippo.apiKey = "shippo_test_cf1b6d0655e59fc6316880580765066038ef20d8";
	}

	@Before
    public void setVersion() {
        Shippo.apiVersion = "2016-10-25";
    }

	public Map<String, Object> getInvalidObjectMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
