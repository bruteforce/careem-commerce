package com.careem.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.careem.exception.APIConnectionException;
import com.careem.exception.APIException;
import com.careem.exception.AuthenticationException;
import com.careem.exception.InvalidRequestException;


public class TrackTest extends ShippoTest {

    final static String carrier = "usps";
    final static String number = "9205590164917312751089";

    private void checkTrack(Track track) {
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), number);
        assertEquals(track.getTrackingStatus().getStatus(), Track.TrackingStatus.DELIVERED);
    }

    @Test
    public void testGet()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, number, null);
        checkTrack(track);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrier()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo("bad", number, null);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrierNumber()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, "invalid", null);
    }

    @Test
    public void testRegisterWebhook()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.registerTrackingWebhook(carrier, number, "meta", null);
        checkTrack(track);
    }
}
