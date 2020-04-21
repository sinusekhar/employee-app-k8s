package com.sinu.play.apps.dao;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CacheDaoImpl implements CacheDao {
    @Value("${redis.url}")
    private String redisUrl;

    @Value("${redis.bucket}")
    private String redisBucket;

    private RedissonClient redisson;

    @PostConstruct
    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress(redisUrl);
        redisson = Redisson.create(config);
    }

    @Override
    public void set(Object obj,String key) throws Exception {
        redisson.getBucket(key).set(obj);
    }

    @Override
    public Object get(String key) throws Exception {
        return redisson.getBucket(key).get();
    }
}
