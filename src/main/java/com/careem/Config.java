package com.careem;

public abstract class Config {
    public static volatile boolean useRedis = true;
    public static final String REDIS_HOST = "localhost";
}
