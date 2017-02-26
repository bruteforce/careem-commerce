package com.careem.lib;

import com.careem.Config;
import redis.clients.jedis.Jedis;

/**
 * Created by deepak on 26/02/17.
 */
public class JedisClient {
    public static Jedis jedis = new Jedis(Config.REDIS_HOST);
}
