package com.careem;

public abstract class Constants {

    public static final String LIVE_API_BASE = "localhost";
    public static final String VERSION = "1.0";
    public static boolean DEBUG = true;
    public static boolean INMEMORY = true;

    public static int RATES_REQ_TIMEOUT = 25000; //  milliseconds
    public static int TRANSACTION_REQ_TIMEOUT = 25000; // milliseconds

    public static volatile String apiKey;
    public static volatile String apiVersion;

    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static boolean inMemory(){
        return INMEMORY;
    }

    public static void setDEBUG(boolean dEBUG) {
        DEBUG = dEBUG;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        Constants.apiKey = apiKey;
    }

    public static String getApiVersion() {
        return apiVersion;
    }

    public static void setApiVersion(String apiVersion) {
        Constants.apiVersion = apiVersion;
    }

}
