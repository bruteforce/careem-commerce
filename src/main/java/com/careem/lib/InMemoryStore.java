package com.careem.lib;

import com.careem.helpers.TimestampHelper;
import com.careem.model.ShippoRawJsonObject;
import com.careem.model.ShippoRawJsonObjectDeserializer;
import com.google.gson.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by deepak on 26/02/17.
 */
public class InMemoryStore {
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(ShippoRawJsonObject.class,
                    new ShippoRawJsonObjectDeserializer()).create();

    public static  String sampleJson = "{}";

    public static String processRequest(String method, String url, Map<String, Object> params) {

        if(method.equals("POST")){
            String distinctKey = nextSessionId();
            params.put("object_id", distinctKey);
            params.put("object_created", TimestampHelper.getTimeStamp());
            params.put("object_updated", TimestampHelper.getTimeStamp());
            JedisClient.jedis.set(distinctKey, GSON.toJson(params));
            return (GSON.toJson(params));
        }else if (method.equals("GET")){
            String object_id = url.substring(url.lastIndexOf('/') + 1);
            String getjson = JedisClient.jedis.get(object_id);
            if(getjson == null){
                getjson = InMemoryStore.sampleJson;
            }
            return (GSON.toJson(params));
        }
        return InMemoryStore.sampleJson;
    }

    private static SecureRandom random = new SecureRandom();
    public static String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }


}
