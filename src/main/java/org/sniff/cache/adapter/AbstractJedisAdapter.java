package org.sniff.cache.adapter;

import org.sniff.common.util.Function;
import redis.clients.jedis.*;
import redis.clients.util.Pool;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @auth snifferhu
 * @date 2017/4/13 10:19
 */
public abstract class AbstractJedisAdapter<J extends JedisCommands & AutoCloseable> implements JedisAdapter {
    protected Pool<J> pool;


    /**
     * @param pool the pool to set
     */
    public void setPool(Pool<J> pool) {
        this.pool = pool;
    }

    /**
     * 在python中eval代表有返回值的执行，所以改用eval
     *
     * @param fun 执行表达式
     * @param <T> 返回数据泛型
     * @return
     */
    protected abstract <T> T eval(Function<J, T> fun);


    public String set(final String key, final String value) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.set(key, value);
            }
        });
    }

    public String get(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.get(key);
            }
        });
    }

    public String setex(final String key, final int seconds, final String value) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.setex(key, seconds, value);
            }
        });
    }

    public Long setnx(final String key, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.setnx(key, value);
            }
        });
    }

    public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.set(key, value, nxxx, expx, time);
            }
        });
    }

    public Boolean exists(final String key) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.exists(key);
            }
        });
    }

    public Long persist(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.persist(key);
            }
        });
    }

    public String type(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.type(key);
            }
        });
    }

    public Long expire(final String key, final int seconds) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.expire(key, seconds);
            }
        });
    }

    public Long expireAt(final String key, final long unixTime) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.expireAt(key, unixTime);
            }
        });
    }

    public Long ttl(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.ttl(key);
            }
        });
    }

    public Boolean setbit(final String key, final long offset, final boolean value) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.setbit(key, offset, value);
            }
        });
    }

    public Boolean setbit(final String key, final long offset, final String value) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.setbit(key, offset, value);
            }
        });
    }

    public Boolean getbit(final String key, final long offset) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.getbit(key, offset);
            }
        });
    }

    public Long setrange(final String key, final long offset, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.setrange(key, offset, value);
            }
        });
    }

    public String getrange(final String key, final long startOffset, final long endOffset) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.getrange(key, startOffset, endOffset);
            }
        });
    }

    public String getSet(final String key, final String value) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.getSet(key, value);
            }
        });
    }

    public Long decrBy(final String key, final long integer) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.decrBy(key, integer);
            }
        });
    }

    public Long decr(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.decr(key);
            }
        });
    }

    public Long incrBy(final String key, final long integer) {
        return eval(new Function<J, Long>() {
                        @Override
                        public Long apply(J j) {
                            return j.incrBy(key, integer);
                        }
                    }
        );
    }

    public Long incr(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.incr(key);
            }
        });
    }

    public Long append(final String key, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.append(key, value);
            }
        });
    }

    public String substr(final String key, final int start, final int end) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.substr(key, start, end);
            }
        });
    }

    public Long hset(final String key, final String field, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.hset(key, field, value);
            }
        });
    }

    public String hget(final String key, final String field) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.hget(key, field);
            }
        });
    }

    public Long hsetnx(final String key, final String field, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.hsetnx(key, field, value);
            }
        });
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.hmset(key, hash);
            }
        });
    }

    public List<String> hmget(final String key, final String... fields) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.hmget(key, fields);
            }
        });
    }

    public Long hincrBy(final String key, final String field, final long value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.hincrBy(key, field, value);
            }
        });
    }

    public Boolean hexists(final String key, final String field) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.hexists(key, field);
            }
        });
    }

    public Long hdel(final String key, final String... field) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.hdel(key, field);
            }
        });
    }

    public Long hlen(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.hlen(key);
            }
        });
    }

    public Set<String> hkeys(final String key) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.hkeys(key);
            }
        });
    }

    public List<String> hvals(final String key) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.hvals(key);
            }
        });
    }

    public Map<String, String> hgetAll(final String key) {
        return eval(new Function<J, Map<String, String>>() {
            @Override
            public Map<String, String> apply(J j) {
                return j.hgetAll(key);
            }
        });
    }

    public Long rpush(final String key, final String... string) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.rpush(key, string);
            }
        });
    }

    public Long lpush(final String key, final String... string) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.lpush(key, string);
            }
        });
    }

    public Long llen(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.llen(key);
            }
        });
    }

    public List<String> lrange(final String key, final long start, final long end) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.lrange(key, start, end);
            }
        });
    }

    public String ltrim(final String key, final long start, final long end) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.ltrim(key, start, end);
            }
        });
    }

    public String lindex(final String key, final long index) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.lindex(key, index);
            }
        });
    }


    public Long lrem(final String key, final long count, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.lrem(key, count, value);
            }
        });
    }

    public String lpop(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.lpop(key);
            }
        });
    }

    public String rpop(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.rpop(key);
            }
        });
    }

    public Long sadd(final String key, final String... member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.sadd(key, member);
            }
        });
    }

    public Set<String> smembers(final String key) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.smembers(key);
            }
        });
    }

    public Long srem(final String key, final String... member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.srem(key, member);
            }
        });
    }

    public String spop(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.spop(key);
            }
        });
    }

    public Long scard(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.scard(key);
            }
        });
    }

    public Boolean sismember(final String key, final String member) {
        return eval(new Function<J, Boolean>() {
            @Override
            public Boolean apply(J j) {
                return j.sismember(key, member);
            }
        });
    }

    public String srandmember(final String key) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.srandmember(key);
            }
        });
    }

    public List<String> srandmember(final String key, final int count) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.srandmember(key, count);
            }
        });
    }

    public Long strlen(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.strlen(key);
            }
        });
    }

    public Long zadd(final String key, final double score, final String member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zadd(key, score, member);
            }
        });
    }

    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zadd(key, scoreMembers);
            }
        });
    }

    public Set<String> zrange(final String key, final long start, final long end) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrange(key, start, end);
            }
        });
    }

    public Long zrem(final String key, final String... member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zrem(key, member);
            }
        });
    }

    public Double zincrby(final String key, final double score, final String member) {
        return eval(new Function<J, Double>() {
            @Override
            public Double apply(J j) {
                return j.zincrby(key, score, member);
            }
        });
    }

    public Long zrank(final String key, final String member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zrank(key, member);
            }
        });
    }

    public Long zrevrank(final String key, final String member) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zrevrank(key, member);
            }
        });
    }

    public Set<String> zrevrange(final String key, final long start, final long end) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrevrange(key, start, end);
            }
        });
    }

    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeWithScores(key, start, end);
            }
        });
    }

    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrevrangeWithScores(key, start, end);
            }
        });
    }

    public Long zcard(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zcard(key);
            }
        });
    }

    public Double zscore(final String key, final String member) {
        return eval(new Function<J, Double>() {
            @Override
            public Double apply(J j) {
                return j.zscore(key, member);
            }
        });
    }

    public List<String> sort(final String key) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.sort(key);
            }
        });
    }

    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.sort(key, sortingParameters);
            }
        });
    }

    public Long zcount(final String key, final double min, final double max) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zcount(key, min, max);
            }
        });
    }

    public Long zcount(final String key, final String min, final String max) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zcount(key, min, max);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByScore(key, min, max);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final String min, final String max) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByScore(key, min, max);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final double min, final double max) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByScore(key, min, max);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByScore(key, min, max, offset, count);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrevrangeByScore(key, max, min);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByScore(key, min, max, offset, count);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrevrangeByScore(key, max, min, offset, count);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrevrangeByScoreWithScores(key, max, min);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrevrangeByScore(key, max, min, offset, count);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeByScoreWithScores(key, max, min);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
        return eval(new Function<J, Set<Tuple>>() {
            @Override
            public Set<Tuple> apply(J j) {
                return j.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        });
    }

    public Long zremrangeByRank(final String key, final long start, final long end) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zremrangeByRank(key, start, end);
            }
        });
    }

    public Long zremrangeByScore(final String key, final double start, final double end) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zremrangeByScore(key, start, end);
            }
        });
    }

    public Long zremrangeByScore(final String key, final String start, final String end) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zremrangeByScore(key, start, end);
            }
        });
    }

    public Long zlexcount(final String key, final String min, final String max) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zlexcount(key, min, max);
            }
        });
    }

    public Set<String> zrangeByLex(final String key, final String min, final String max) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByLex(key, min, max);
            }
        });
    }

    public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
        return eval(new Function<J, Set<String>>() {
            @Override
            public Set<String> apply(J j) {
                return j.zrangeByLex(key, min, max, offset, count);
            }
        });
    }

    public Long zremrangeByLex(final String key, final String min, final String max) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.zremrangeByLex(key, min, max);
            }
        });
    }

    public Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot, final String value) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.linsert(key, where, pivot, value);
            }
        });
    }

    public Long lpushx(final String key, final String... string) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.lpushx(key, string);
            }
        });
    }

    public Long rpushx(final String key, final String... string) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.rpushx(key, string);
            }
        });
    }

    public List<String> blpop(final String arg) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.blpop(arg);
            }
        });
    }

    public List<String> blpop(final int timeout, final String key) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.blpop(timeout, key);
            }
        });
    }

    public List<String> brpop(final String arg) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.brpop(arg);
            }
        });
    }

    public List<String> brpop(final int timeout, final String key) {
        return eval(new Function<J, List<String>>() {
            @Override
            public List<String> apply(J j) {
                return j.brpop(timeout, key);
            }
        });
    }

    public Long del(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.del(key);
            }
        });
    }

    public String echo(final String string) {
        return eval(new Function<J, String>() {
            @Override
            public String apply(J j) {
                return j.echo(string);
            }
        });
    }

    public Long move(final String key, final int dbIndex) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.move(key, dbIndex);
            }
        });
    }

    public Long bitcount(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.bitcount(key);
            }
        });
    }

    public Long bitcount(final String key, final long start, final long end) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.bitcount(key, start, end);
            }
        });
    }

    public ScanResult<Map.Entry<String, String>> hscan(final String key, final int cursor) {
        return eval(new Function<J, ScanResult<Map.Entry<String, String>>>() {
            @Override
            public ScanResult<Map.Entry<String, String>> apply(J j) {
                return j.hscan(key, cursor);
            }
        });
    }

    public ScanResult<String> sscan(final String key, final int cursor) {
        return eval(new Function<J, ScanResult<String>>() {
            @Override
            public ScanResult<String> apply(J j) {
                return j.sscan(key, cursor);
            }
        });
    }

    @Deprecated
    public ScanResult<Tuple> zscan(final String key, final int cursor) {
        return eval(new Function<J, ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> apply(J j) {
                return j.zscan(key, cursor);
            }
        });
    }

    public ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor) {
        return eval(new Function<J, ScanResult<Map.Entry<String, String>>>() {
            @Override
            public ScanResult<Map.Entry<String, String>> apply(J j) {
                return j.hscan(key, cursor);
            }
        });
    }

    public ScanResult<String> sscan(final String key, final String cursor) {
        return eval(new Function<J, ScanResult<String>>() {
            @Override
            public ScanResult<String> apply(J j) {
                return j.sscan(key, cursor);
            }
        });
    }

    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return eval(new Function<J, ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> apply(J j) {
                return j.zscan(key, cursor);
            }
        });
    }

    public Long pfadd(final String key, final String... elements) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.pfadd(key, elements);
            }
        });
    }

    public long pfcount(final String key) {
        return eval(new Function<J, Long>() {
            @Override
            public Long apply(J j) {
                return j.pfcount(key);
            }
        });
    }

//*********************************************

    public abstract Long del(String... keys);

    public abstract List<String> blpop(int timeout, String... keys);

    public abstract List<String> brpop(int timeout, String... keys);

    public abstract List<String> blpop(String... args);

    public abstract List<String> brpop(String... args);

    public abstract Set<String> keys(String pattern);

    public abstract List<String> mget(String... keys);

    public abstract String mset(String... keysvalues);

    public abstract Long msetnx(String... keysvalues);

    public abstract String rename(String oldkey, String newkey);

    public abstract Long renamenx(String oldkey, String newkey);

    public abstract String rpoplpush(String srckey, String dstkey);

    public abstract Set<String> sdiff(String... keys);

    public abstract Long sdiffstore(String dstkey, String... keys);

    public abstract Set<String> sinter(String... keys);

    public abstract Long sinterstore(String dstkey, String... keys);

    public abstract Long smove(String srckey, String dstkey, String member);

    public abstract Long sort(String key, SortingParams sortingParameters, String dstkey);

    public abstract Long sort(String key, String dstkey);

    public abstract Set<String> sunion(String... keys);

    public abstract Long sunionstore(String dstkey, String... keys);

    public abstract String watch(String... keys);

    public abstract String unwatch();

    public abstract Long zinterstore(String dstkey, String... sets);

    public abstract Long zinterstore(String dstkey, ZParams params, String... sets);

    public abstract Long zunionstore(String dstkey, String... sets);

    public abstract Long zunionstore(String dstkey, ZParams params, String... sets);

    public abstract String brpoplpush(String source, String destination, int timeout);

    public abstract Long publish(String channel, String message);

    public abstract void subscribe(JedisPubSub jedisPubSub, String... channels);

    public abstract void psubscribe(JedisPubSub jedisPubSub, String... patterns);

    public abstract String randomKey();

    public abstract Long bitop(BitOP op, String destKey, String... srcKeys);

    public abstract ScanResult<String> scan(int cursor);

    public abstract ScanResult<String> scan(String cursor);

    public abstract String pfmerge(String destkeyexecute, String... sourcekeys);

    public abstract long pfcount(String... keys);
}
