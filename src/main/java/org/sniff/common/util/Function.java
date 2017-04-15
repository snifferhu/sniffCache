package org.sniff.common.util;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:35
 */
public interface Function<T,R> {

    R apply(T t);
}
