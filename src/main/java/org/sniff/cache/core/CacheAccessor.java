package org.sniff.cache.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

/**
 * @auth snifferhu
 * @date 2017/5/10 05:48
 */
public class CacheAccessor implements InitializingBean {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private RedisConnectionFactory connectionFactory;

    public CacheAccessor() {
    }

    public void afterPropertiesSet() {
        Assert.notNull(this.getConnectionFactory(), "RedisConnectionFactory is required");
    }

    public RedisConnectionFactory getConnectionFactory() {
        return this.connectionFactory;
    }

    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}