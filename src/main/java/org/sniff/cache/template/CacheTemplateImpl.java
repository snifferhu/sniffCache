package org.sniff.cache.template;

import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.cache.map.CacheMap;
import org.sniff.cache.map.CacheMapImpl;
import org.sniff.common.util.CacheSerializer;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:16
 */
public class CacheTemplateImpl implements CacheTemplate {
    private JedisAdapter adapter;
    private CacheSerializer serial;
    private CacheMap map;

    public CacheSerializer getSerial() {
        return serial;
    }

    public void setSerial(CacheSerializer serial) {
        this.serial = serial;
    }

    public JedisAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(JedisAdapter adapter) {
        this.adapter = adapter;
    }

    public <T> CacheMap getMapOperation(T obj) {
        if (obj instanceof String){
            map = new CacheMapImpl<>(this.adapter, String.valueOf(obj), serial);
        }else {
            map = new CacheMapImpl<>(this.adapter, serial.to(obj), serial);
        }
        return map;
    }
}
