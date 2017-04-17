package org.sniff.cache.map;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.cache.adapter.JedisAdapterAware;
import org.sniff.cache.template.KeyData;
import redis.clients.jedis.ScanResult;

import java.util.*;

/**
 * @auth snifferhu
 * @date 2017/4/16 11:00
 */
public class CacheMapImpl<K, V> implements CacheMap, JedisAdapterAware, KeyData {

    private JedisAdapter jedisAdapter;
    private String cacheKey;
    private static Gson gson = new Gson();

    public CacheMapImpl(JedisAdapter jedisAdapter, String cacheKey) {
        this.jedisAdapter = jedisAdapter;
        this.cacheKey = cacheKey;
    }

    public JedisAdapter getJedisAdapter() {
        return jedisAdapter;
    }

    @Override
    public void setJedisAdapter(JedisAdapter jedisAdapter) {
        this.jedisAdapter = jedisAdapter;
    }

    @Override
    public String getCacheKey() {
        return cacheKey;
    }

    @Override
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Override
    public long size() {
        return jedisAdapter.hlen(cacheKey);
    }

    @Override
    public boolean isEmpty() {
        return jedisAdapter.exists(cacheKey);
    }

    @Override
    public boolean containsKey(Object key) {
        return jedisAdapter.hexists(cacheKey, String.valueOf(key));
    }

    @Override
    public boolean containsValue(Object value) {
        ScanResult<Map.Entry<String, String>> scanResult = jedisAdapter.hscan(cacheKey, "0");
        if (scanResult.getResult().contains(value)) {
            return true;
        } else if (!scanResult.getStringCursor().equals("0")) {
            return containsValue(scanResult, value);
        } else {
            return false;
        }
    }

    private boolean containsValue(ScanResult<Map.Entry<String, String>> scanResult, Object value) {
        if (scanResult.getStringCursor().equals("0")) {
            return scanResult.getResult().contains(value);
        } else {
            return containsValue(scanResult, value);
        }
    }

    @Override
    public <T> T get(Object field, Class<T> clazz) {
        String value;
        if (field instanceof String) {
            value = jedisAdapter.hget(cacheKey, String.valueOf(field));
        } else {
            value = jedisAdapter.hget(cacheKey, gson.toJson(field));
        }

        if (StringUtils.isBlank(value)) {
            return gson.fromJson(value, clazz);
        } else {
            return null;
        }
    }

    @Override
    public <T> Long put(Object field, T value) {
        if (field instanceof String) {
            return jedisAdapter.hset(cacheKey, String.valueOf(field), gson.toJson(value));
        } else {
            return jedisAdapter.hset(cacheKey, gson.toJson(field), gson.toJson(value));
        }
    }

    @Override
    public Long remove(String... field) {
        return jedisAdapter.hdel(cacheKey, field);
    }


    @Override
    public String putAll(Map m) {
        return jedisAdapter.hmset(cacheKey, m);
    }


    @Override
    public Long clear() {
        return jedisAdapter.del(cacheKey);
    }


    @Override
    public Set<String> keySet() {
        return jedisAdapter.hkeys(cacheKey);
    }

    @Override
    public <T> Set<T> keySet(Class<T> clazz) {
        Set<String> cacheValue = jedisAdapter.hkeys(cacheKey);
        if (cacheValue.isEmpty()) {
            return null;
        }

        Set<T> valueSet = new HashSet<>();
        for (String value : cacheValue) {
            valueSet.add(gson.fromJson(value, clazz));
        }
        return valueSet;
    }

    @Override
    public Collection<String> values() {
        return jedisAdapter.hvals(cacheKey);
    }

    @Override
    public <T> Collection<T> values(Class<T> clazz) {
        List<String> cacheValue = jedisAdapter.hvals(cacheKey);
        if (cacheValue.isEmpty()) {
            return null;
        }

        List<T> valueList = new ArrayList<>();
        for (String value : cacheValue) {
            valueList.add(gson.fromJson(value, clazz));
        }
        return valueList;
    }

//    public ScanResult<Map.Entry<String, String>> entrySet() {
//        return jedisAdapter.hscan(cacheKey,"10");
//    }


    @Override
    public <T> List<T> getAll(Object... fields){
        return null;
    }
}
