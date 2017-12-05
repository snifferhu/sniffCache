package org.sniff.cache.map;

import org.apache.commons.lang.StringUtils;
import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.cache.adapter.JedisAdapterAware;
import org.sniff.cache.serializer.CacheSerializer;
import org.sniff.cache.template.KeyData;
import redis.clients.jedis.ScanResult;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

/**
 * @auth snifferhu
 * @date 2017/4/16 11:00
 */
public class CacheMapImpl<K, V> implements CacheMap, JedisAdapterAware, KeyData {

    private JedisAdapter jedisAdapter;
    private String cacheKey;
    private CacheSerializer hKeySerial = null;
    private CacheSerializer hValueSerial = null;

    public CacheMapImpl(JedisAdapter jedisAdapter, String cacheKey, CacheSerializer hKeySerial,CacheSerializer hValueSerial) {
        this.jedisAdapter = jedisAdapter;
        this.cacheKey = cacheKey;
        this.hKeySerial = hKeySerial;
        this.hValueSerial = hValueSerial;
    }

    public CacheSerializer gethKeySerial() {
        return hKeySerial;
    }

    public void sethKeySerial(CacheSerializer hKeySerial) {
        this.hKeySerial = hKeySerial;
    }

    public CacheSerializer gethValueSerial() {
        return hValueSerial;
    }

    public void sethValueSerial(CacheSerializer hValueSerial) {
        this.hValueSerial = hValueSerial;
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
    public String get(Object field) {
        String value;
        if (field instanceof String) {
            value = jedisAdapter.hget(cacheKey, String.valueOf(field));
        } else {
            value = jedisAdapter.hget(cacheKey, hKeySerial.to(field));
        }
        return value;
    }

    @Override
    public <T> T get(Object field, Class<T> clazz) {
        String value;
        if (field instanceof String) {
            value = jedisAdapter.hget(cacheKey, String.valueOf(field));
        } else {
            value = jedisAdapter.hget(cacheKey, hKeySerial.to(field));
        }

        if (StringUtils.isNotBlank(value)) {
            return hValueSerial.from(value, clazz);
        } else {
            return null;
        }
    }

    @Override
    public <T> boolean put(Object field, T value) {
        String targetField;
        String targetValue;
        if (field instanceof String) {
            targetField = String.valueOf(field);
        } else {
            targetField = hKeySerial.to(field);
        }

        if (value instanceof String) {
            targetValue = String.valueOf(value);
        } else {
            targetValue = hValueSerial.to(value);
        }

        return jedisAdapter.hset(cacheKey, targetField, targetValue) == 1L;
    }

    @Override
    public boolean remove(String... field) {
        return jedisAdapter.hdel(cacheKey, field) == 1L;
    }

    @Override
    public <T> String putAllForObj(T m) {
        return putAllMap(hValueSerial.from(hValueSerial.to(m), new HashMap<String, String>().getClass()));
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     * @todo
     */
    @Override
    public <T> T getAllForObj(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, String> resultValue = new HashMap<>();
        for (int i = 0, size = fields.length; i < size; i++) {
            String field = fields[i].getName();
            resultValue.put(field, jedisAdapter.hget(cacheKey, field).replaceAll("\"", ""));
        }
        return hValueSerial.from(hValueSerial.to(resultValue), clazz);
    }

    @Override
    public <T> String putAll(Map<String, T> m) {
        return putAllMap(m);
    }

    private <V> String putAllMap(Map<String, V> m) {
        Map<String, String> transformMap = new HashMap<>();
        if (m != null) {
            if (m.values().toArray()[0] instanceof String) {
                return jedisAdapter.hmset(cacheKey, (Map<String, String>) m);
            }
            for (Entry<String, V> entry : m.entrySet()) {
                transformMap.put(entry.getKey(), hValueSerial.to(entry.getValue()));
            }
            return jedisAdapter.hmset(cacheKey, transformMap);
        }
        return null;
    }


    @Override
    public boolean clear() {
        return jedisAdapter.del(cacheKey) == 1L;
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
            valueSet.add(hValueSerial.from(value, clazz));
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
            valueList.add(hValueSerial.from(value, clazz));
        }
        return valueList;
    }


    @Override
    public boolean containsValue(Object value) {
        ScanResult<Entry<String, String>> scanResult = jedisAdapter.hscan(cacheKey, "0");
        return scanOne(scanResult, hValueSerial.to(value));
    }

    private boolean scanOne(ScanResult<Entry<String, String>> scanResult, String value) {
        boolean isContains = false;
        for (Entry<String, String> entry : scanResult.getResult()) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        if (scanResult.getStringCursor().equals("0")) {
            return isContains;
        } else {
            return scanOne(jedisAdapter.hscan(cacheKey, scanResult.getStringCursor()), value);
        }
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz, String... fields) {
        if (fields == null) {
            return null;
        }
        List<String> tmpResults;
        if (!(fields[0] instanceof String)) {
            String[] tmpFields = new String[fields.length];
            for (int i = 0, size = fields.length; i < size; i++) {
                tmpFields[i] = hKeySerial.to(fields[i]);
            }
        }
        tmpResults = jedisAdapter.hmget(cacheKey, fields);
        if (tmpResults != null && tmpResults.size() != 0) {
            List<T> results = new ArrayList<>(tmpResults.size());
            for (String result : tmpResults) {
                results.add(hValueSerial.from(result, clazz));
            }
            return results;
        } else {
            return null;
        }
    }

    private <T> List<T> scanAll(ScanResult<Entry<String, String>> scanResult, Class<T> clazz, Object... fields) {
        List<T> resultList = new ArrayList<>();
        for (Entry<String, String> entry : scanResult.getResult()) {
            for (Object field : fields) {
                if (field instanceof String && entry.getKey().equals(field)) {
                    resultList.add(hValueSerial.from(entry.getValue(), clazz));
                } else if (entry.getKey().equals(hKeySerial.to(field))) {
                    resultList.add(hValueSerial.from(entry.getValue(), clazz));
                }
            }
        }
        if ("0".equals(scanResult.getStringCursor())) {
            return resultList;
        } else {
            resultList.addAll(scanAll(jedisAdapter.hscan(cacheKey, scanResult.getStringCursor()), clazz, fields));
            return resultList;
        }
    }

    @Override
    public <V> boolean putIfAbsent(Object field, V value) {
        if (field instanceof String) {
            return jedisAdapter.hsetnx(cacheKey, String.valueOf(field), hValueSerial.to(value)) == 1L;
        } else {
            return jedisAdapter.hsetnx(cacheKey, hKeySerial.to(field), hValueSerial.to(value)) == 1L;
        }
    }

    @Override
    public long incrBy(Object field, long value) {
        if (field instanceof String) {
            return jedisAdapter.hincrBy(cacheKey, String.valueOf(field), value);
        } else {
            return jedisAdapter.hincrBy(cacheKey, hKeySerial.to(field), value);
        }
    }
}
