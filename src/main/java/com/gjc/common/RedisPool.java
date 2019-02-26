package com.gjc.common;

import com.gjc.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 初始化连接池
 */
public class RedisPool {
    private static JedisPool pool;      //jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));//最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "20"));//在jedisPool这个连接池中，最大的idle（空闲）的jedis的实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "20"));//在jedisPool这个连接池中，最小的idle（空闲）的jedis的实例个数

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));//在borrow一个jedis实例的时候，是否需要进行验证操作，如果赋值为true。则得到的jedis实例肯定一可以使用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));//在return一个jedis实例的时候，是否需要进行验证操作，如果赋值为true。则放回jedispool的jedis实例肯定一可以使用的

    private static String redisIp = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));

    /**
     * 初始化连接池
     */
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽的时候是否阻塞，false会抛出异常，true阻塞直到超时，默认为true
        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
    }

    //出事话JVM的时候就加载该类
    static {
        initPool();
    }

    /**
     * 把redis连接池，jedis实例开放出去
     * 从连接池中拿出一个实例
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * 把jedis放回连接池中
     */
    public static void returnBrokenResource(Jedis jedis) {
        if (null != jedis) {
            pool.returnBrokenResource(jedis);
        }
    }

    /**
     * 如果是一个坏连接，需要放到BrokenResource
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (null != jedis) {
            pool.returnResource(jedis);
        }
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("jesseKey", "jesseValue");
        returnResource(jedis);
        pool.destroy();//临死调用，销毁连接池中的所有连接
        System.out.println("program is end");

    }

}
