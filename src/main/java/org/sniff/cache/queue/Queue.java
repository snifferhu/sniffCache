package org.sniff.cache.queue;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:27
 */
public interface Queue<E> {
    boolean push(E object);

    E lpop();
}
