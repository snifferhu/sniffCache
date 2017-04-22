package org.sniff.cache.value;

import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.common.util.CacheSerializer;

/**
 * @auth snifferhu
 * @date 2017/4/20 21:00
 */
public class CacheValueImpl<V> implements CacheValue {
    private JedisAdapter jedisAdapter;
    private String cacheKey;
    private CacheSerializer serial;

    public CacheValueImpl(JedisAdapter jedisAdapter, String cacheKey, CacheSerializer serial) {
        this.jedisAdapter = jedisAdapter;
        this.cacheKey = cacheKey;
        this.serial = serial;
    }

    public String set(V v){
        return jedisAdapter.set(cacheKey,serial.to(v));
    }
}
