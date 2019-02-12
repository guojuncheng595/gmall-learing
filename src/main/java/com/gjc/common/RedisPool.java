package com.gjc.common;

import com.gjc.util.PropertiesUtil;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 */
public class RedisPool {
    private static JedisPool pool;      //jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "20"));//在jedisPool这个连接池中，最大的idle（空闲）的jedis的实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "20"));//在jedisPool这个连接池中，最小的idle（空闲）的jedis的实例个数

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));//在borrow一个jedis实例的时候，是否需要进行验证操作，如果赋值为true。则得到的jedis实例肯定一可以使用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));//在return一个jedis实例的时候，是否需要进行验证操作，如果赋值为true。则放回jedispool的jedis实例肯定一可以使用的

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

    }

}
