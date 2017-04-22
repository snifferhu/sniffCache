package org.sniff.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @auth snifferhu
 * @date 2017/4/20 21:40
 */
public class GsonSerializer implements CacheSerializer {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSSS").create();
    @Override
    public <T> String to(T t) {
        return gson.toJson(t);
    }

    @Override
    public <R> R from(String value, Class<R> clazz) {
        return gson.fromJson(value,clazz);
    }
}
