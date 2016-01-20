package com.quxionglie.rpc.protobuf3;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
    private static JedisPool pool;
    private final static Logger logger = LoggerFactory.getLogger(JedisClient.class);

    static {
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(200);
        config.setMaxIdle(10);
//        config.setMaxWait(1000L);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, Config.REDIS_HOST, Config.REDIS_PORT);
        logger.info("JedisPool,host={},port={}", Config.REDIS_HOST, Config.REDIS_PORT);
    }

    private static byte[] getId(String key) {
        return key.getBytes(Charsets.UTF_8);
    }

    public static byte[] getData(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] id = key.getBytes(Charsets.UTF_8);
            return jedis.get(id);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static boolean setData(String key, byte[] bytes) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String status = jedis.set(getId(key), bytes);
            return "OK".equals(status);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
