package com.careem.lib;


import com.careem.model.Rate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 26/02/17.
 */
public class JedisClient {
    public static Jedis jedis = new Jedis("localhost");
}
