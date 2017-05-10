package org.sniff.cache.serializer.impl;

import org.apache.commons.lang.StringUtils;

/**
 * @auth snifferhu
 * @date 2017/5/10 05:26
 */
public class SerializationUtils {
    static final byte[] EMPTY_ARRAY = new byte[0];
    static final String EMPTY = "";

    public SerializationUtils() {
    }

    static boolean isEmpty(String data) {
        return StringUtils.isEmpty(data);
    }
}
