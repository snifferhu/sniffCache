package org.sniff.cache.map;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:23
 */
public interface CacheMap {
    String get(Object field);

    <T> T get(Object field, Class<T> clazz);

    <T> boolean put(Object field, T value);

    boolean containsValue(Object value);

    boolean containsKey(Object key);

    boolean isEmpty();

    long size();

    boolean remove(String... field);

    <T> String putAllForObj(T m) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    <T> T getAllForObj(Class<T> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException;

    <T> String putAll(Map<String,T> m);

    boolean clear();

    Set<String> keySet();

    <T> Set<T> keySet(Class<T> clazz);

    Collection<String> values();

    <T> Collection<T> values(Class<T> clazz);

    <T> List<T> getAll(Class<T> clazz, String... fields);

    <V> boolean putIfAbsent(Object field, V value);

//    /**
//     * 缓存勾搭数据源查询，混合使用方法
//     * 如果缓存为null，则查询数据源，缓存内容，并返回内容
//     * 如果缓存不为null，则返回内容
//     *
//     * @param key      缓存key
//     * @param clazz         返回类型
//     * @param supplier 数据源处理表达式
//     * @param <T>
//     * @return
//     */
//    <T> T cacheFind(String key, Class<T> clazz, Supplier<T> supplier);
//
//    /**
//     * 缓存勾搭数据源查询，混合使用方法
//     * 如果缓存为null，则查询数据源，缓存内容设置超时时间，并返回内容
//     * 如果缓存不为null，则返回内容
//     * @param key           缓存key
//     * @param clazz         返回类型
//     * @param supplier      数据源处理表达式
//     * @return
//     */
//    <T> List<T> cacheQuery(String key, Class<T> clazz, Supplier<List> supplier);
}
