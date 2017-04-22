package org.sniff.cache.value;

import java.util.concurrent.TimeUnit;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:59
 */
public interface CacheValue<V> {
    String set(V v);

    String set(V v, int second);

    String set(V o, int time, TimeUnit unit);

    boolean setIfAbsent(V o);
}
