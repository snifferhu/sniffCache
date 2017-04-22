package org.sniff.cache.adapter;

import org.sniff.common.util.Function;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Set;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:46
 */
public class ShardedJedisAdapter extends AbstractJedisAdapter<ShardedJedis> {
    /**
     * 在python中eval代表有返回值的执行，所以改用eval
     *
     * @param fun 执行表达式
     * @return
     */
    @Override
    protected <T> T eval(Function<ShardedJedis, T> fun) {
        try (ShardedJedis jedis = pool.getResource()) {
            return fun.apply(jedis);
        }
    }

    @Override
    public Long del(String... keys) {
        return null;
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return null;
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return null;
    }

    @Override
    public List<String> blpop(String... args) {
        return null;
    }

    @Override
    public List<String> brpop(String... args) {
        return null;
    }

    @Override
    public Set<String> keys(String pattern) {
        return null;
    }

    @Override
    public List<String> mget(String... keys) {
        return null;
    }

    @Override
    public String mset(String... keysvalues) {
        return null;
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return null;
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return null;
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return null;
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return null;
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return null;
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return null;
    }

    @Override
    public Set<String> sinter(String... keys) {
        return null;
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return null;
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return null;
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return null;
    }

    @Override
    public Long sort(String key, String dstkey) {
        return null;
    }

    @Override
    public Set<String> sunion(String... keys) {
        return null;
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return null;
    }

    @Override
    public String watch(String... keys) {
        return null;
    }

    @Override
    public String unwatch() {
        return null;
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return null;
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return null;
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return null;
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return null;
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return null;
    }

    @Override
    public Long publish(String channel, String message) {
        return null;
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {

    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {

    }

    @Override
    public String randomKey() {
        return null;
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return null;
    }

    @Override
    public ScanResult<String> scan(int cursor) {
        return null;
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        return null;
    }

    @Override
    public String pfmerge(String destkeyexecute, String... sourcekeys) {
        return null;
    }

    @Override
    public long pfcount(String... keys) {
        return 0;
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return null;
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return null;
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return null;
    }

    @Override
    public String lset(String key, long index, String value) {
        return null;
    }

    @Override
    public Set<String> spop(String key, long count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return null;
    }
}
