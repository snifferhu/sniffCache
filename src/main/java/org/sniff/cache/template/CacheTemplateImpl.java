package org.sniff.cache.template;

import org.sniff.cache.adapter.JedisAdapter;
import org.sniff.cache.map.CacheMap;
import org.sniff.cache.map.CacheMapImpl;
import org.sniff.cache.value.CacheValue;
import org.sniff.cache.value.CacheValueImpl;
import org.sniff.cache.serializer.CacheSerializer;

/**
 * @auth snifferhu
 * @date 2017/4/20 20:16
 */
public class CacheTemplateImpl implements CacheTemplate {
    private JedisAdapter adapter;
    private CacheSerializer keySerial = null;
    private CacheSerializer valueSerial = null;
    private CacheSerializer hKeySerial = null;
    private CacheSerializer hValueSerial = null;
    private CacheMap map;
    private CacheValue value;

    public CacheTemplateImpl() {
        init();
    }

    private void init() {
    }

    public CacheSerializer getKeySerial() {
        return keySerial;
    }

    public void setKeySerial(CacheSerializer keySerial) {
        this.keySerial = keySerial;
    }

    public CacheSerializer getValueSerial() {
        return valueSerial;
    }

    public void setValueSerial(CacheSerializer valueSerial) {
        this.valueSerial = valueSerial;
    }

    public CacheSerializer gethKeySerial() {
        return hKeySerial;
    }

    public void sethKeySerial(CacheSerializer hKeySerial) {
        this.hKeySerial = hKeySerial;
    }

    public CacheSerializer gethValueSerial() {
        return hValueSerial;
    }

    public void sethValueSerial(CacheSerializer hValueSerial) {
        this.hValueSerial = hValueSerial;
    }


    public JedisAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(JedisAdapter adapter) {
        this.adapter = adapter;
    }

    public <T> CacheMap getMapOperation(T obj) {
        return new CacheMapImpl<>(this.adapter, hKeySerial.to(obj),hKeySerial, hValueSerial);
    }

    public <T> CacheValue getValueOperation(T obj) {
        return new CacheValueImpl<>(this.adapter, keySerial.to(obj), valueSerial);
    }
}
