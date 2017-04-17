package org.sniff.cache.adapter;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyCommands;

/**
 * @auth snifferhu
 * @date 2017/4/13 10:19
 */
public interface JedisAdapter extends JedisCommands, MultiKeyCommands {
}
