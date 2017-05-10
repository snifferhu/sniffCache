package org.sniff.cache.serializer.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.sniff.cache.serializer.CacheSerializer;
import org.sniff.cache.serializer.SerializationException;

/**
 * @auth snifferhu
 * @date 2017/4/20 21:40
 */
public class GsonSerializer implements CacheSerializer {

    private Gson gson;

    public GsonSerializer(Gson gson) {
        this.gson = gson;
    }

    public GsonSerializer() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSSS").create();
    }

    @Override
    public <T> String to(T t) {
        if (t == null) {
            return SerializationUtils.EMPTY;
        } else {
            try {
                return gson.toJson(t);
            } catch (Exception e) {
                throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
            }
        }

    }

    @Override
    public <T> T from(String value, Class<T> clazz) {
        if (SerializationUtils.isEmpty(value)) {
            return null;
        } else {
            try {
                return gson.fromJson(value, clazz);
            } catch (Exception e) {
                throw new SerializationException("Could not read JSON: " + e.getMessage(), e);
            }

        }
    }

}
