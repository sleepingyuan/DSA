package com.yuan.solution;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRUcache，主要用LinkedHashMap
 */
public class LRUCache {
    private Map<Integer, Integer> cache;
    private int size;

    public LRUCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap<>();
    }

    public int get(int key) throws KeyNotFoundException {
        if (!cache.containsKey(key)) {
            throw new KeyNotFoundException("Key not found with \"" + key + "\"");
        }
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }

    public void set(int key, int value) {
        cache.remove(key);
        cache.put(key, value);
        if (cache.size() > size) {
            Iterator<Integer> iter = cache.keySet().iterator();
            Integer first = iter.next();
            cache.remove(first);
        }
    }
}

class KeyNotFoundException extends Exception {
    KeyNotFoundException(String message) {
        super(message);
    }
}
