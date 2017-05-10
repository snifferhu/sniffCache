package org.sniff.cache.serializer;

/**
 * @auth snifferhu
 * @date 2017/5/10 05:29
 */
public class SerializationException extends RuntimeException {
    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SerializationException(String msg) {
        super(msg);
    }
}
