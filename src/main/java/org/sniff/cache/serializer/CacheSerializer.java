package org.sniff.cache.serializer;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:33
 */
public interface CacheSerializer {

    <T> String to(T t);

    <T> T from(String value,Class<T> clazz);
}
