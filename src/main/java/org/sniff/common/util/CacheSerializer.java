package org.sniff.common.util;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:33
 */
public interface CacheSerializer {

    <T> String to(T t);

    <R> R from(String value,Class<R> clazz);
}
