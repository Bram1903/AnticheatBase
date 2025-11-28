package com.deathmotion.anticheatbase.plugin.common.util.datastructure;

import java.util.TreeMap;

public class EvictingMap<K, V> extends TreeMap<K, V> {
    private final int maxSize;

    public EvictingMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public V put(K key, V value) {
        if (size() >= maxSize) remove(firstKey());
        return super.put(key, value);
    }

}