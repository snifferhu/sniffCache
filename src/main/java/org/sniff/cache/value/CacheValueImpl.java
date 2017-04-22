package org.sniff.cache.value;

import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.common.util.CacheSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @auth snifferhu
 * @date 2017/4/20 21:00
 */
public class CacheValueImpl<V> implements CacheValue<V> {
    private JedisAdapter jedisAdapter;
    private String cacheKey;
    private CacheSerializer serial;

    public CacheValueImpl(JedisAdapter jedisAdapter, String cacheKey, CacheSerializer serial) {
        this.jedisAdapter = jedisAdapter;
        this.cacheKey = cacheKey;
        this.serial = serial;
    }


    @Override
    public String set(V o) {
        if (o instanceof String) {
            return jedisAdapter.set(cacheKey, String.valueOf(o));
        }
        return jedisAdapter.set(cacheKey, serial.to(o));
    }

    @Override
    public String set(V o, int second) {
        if (o instanceof String) {
            return jedisAdapter.setex(cacheKey, second, String.valueOf(o));
        }
        return jedisAdapter.setex(cacheKey, second, serial.to(o));
    }

    @Override
    public String set(V o, int time, TimeUnit unit) {
        return set(o, (int) unit.toSeconds(time));
    }

    @Override
    public boolean setIfAbsent(V o) {
        if (o instanceof String) {
            return jedisAdapter.setnx(cacheKey, String.valueOf(o)) == 1;
        }
        return jedisAdapter.setnx(cacheKey, serial.to(o)) == 1;
    }
}
