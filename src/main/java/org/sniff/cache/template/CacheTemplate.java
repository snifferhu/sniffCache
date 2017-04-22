package org.sniff.cache.template;

import org.sniff.cache.map.CacheMap;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:21
 */
public interface CacheTemplate {
    <T> CacheMap getMapOperation(T obj);
}
