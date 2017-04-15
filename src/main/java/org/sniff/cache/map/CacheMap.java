package org.sniff.cache.map;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:23
 */
public interface CacheMap {
    String get(String key);

    Object put(String field, Object value);

    boolean delete(String key);

    boolean deleteBatch(String... fields);

    void putAll(Map map);

    List<String> getAll(String... fields);

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
