package com.careem.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.careem.Shippo;
import com.careem.exception.APIConnectionException;
import com.careem.exception.APIException;
import com.careem.exception.AuthenticationException;
import com.careem.exception.InvalidRequestException;
import com.careem.exception.ShippoException;

public class CustomsDeclarationTest extends ShippoTest {

    @Test
    public void testValidCreate() {
		Shippo.setDEBUG(true);
        CustomsDeclaration testObject = (CustomsDeclaration) getDefaultObject();
        assertEquals(testObject.getObjectState(), "VALID");
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclaration.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
		Shippo.setDEBUG(true);
        CustomsDeclaration testObject = (CustomsDeclaration) getDefaultObject();
        CustomsDeclaration retrievedObject;

        retrievedObject = CustomsDeclaration.retrieve((String) testObject.objectId);

        assertEquals(testObject.objectId, retrievedObject.objectId);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclaration.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclarationCollection objectCollection = CustomsDeclaration.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        CustomsDeclarationCollection CustomsDeclarationCollection = CustomsDeclaration.all(objectMap);
        assertEquals(CustomsDeclarationCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        CustomsItem customsItem = (CustomsItem) CustomsItemTest.getDefaultObject();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("exporter_reference", null);
        objectMap.put("importer_reference", null);
        objectMap.put("contents_type", "MERCHANDISE");
        objectMap.put("contents_explanation", "T-Shirt purchase");
        objectMap.put("invoice", "#123123");
        objectMap.put("license", null);
        objectMap.put("certificate", null);
        objectMap.put("notes", null);
        objectMap.put("eel_pfc", "NOEEI_30_37_a");
        objectMap.put("aes_itn", null);
        objectMap.put("non_delivery_option", "ABANDON");
        objectMap.put("certify", "true");
        objectMap.put("certify_signer", "Laura Behrens Wu");
        objectMap.put("disclaimer", null);
        objectMap.put("incoterm", null);

        Map<String, Object> customsItemsMap = new HashMap<String, Object>();
        String[] arr = {customsItem.getObjectId()};
        objectMap.put("items", arr);

        objectMap.put("metadata", "Order ID #123123");

        try {
            CustomsDeclaration testObject = CustomsDeclaration.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}