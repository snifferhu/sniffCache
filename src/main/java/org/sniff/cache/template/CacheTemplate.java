package org.sniff.cache.template;

import org.sniff.cache.map.CacheMap;
import org.sniff.cache.value.CacheValue;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:21
 */
public interface CacheTemplate {
    <T> CacheMap getMapOperation(T obj);

    <T> CacheValue getValueOperation(T obj);
}
