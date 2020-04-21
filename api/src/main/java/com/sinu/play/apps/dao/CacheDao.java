package com.sinu.play.apps.dao;

public interface CacheDao {
    public void set(Object obj, String key) throws Exception;
    public Object get(String key) throws Exception;

}
