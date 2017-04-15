package org.sniff.cache.set;

import java.util.Map;
import java.util.Set;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:27
 */
public interface SortSet<E> {

    boolean zaddAll(Map<String, Double> element);

    int zcount(E left, E right);

    int zcard();

    Double zincrby(String key, Double increment);

    /**
     * <p>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * 超出范围的下标并不会引起错误。
     * </p>
     */
    Set<String> zrange(int begin, int end);

    Set<String> zrevrange(int begin, int end);

    int zrank(String key);

    boolean zrem(String... key);

    Double zscore(String key);

    boolean zadd(String key, Double score);

}
