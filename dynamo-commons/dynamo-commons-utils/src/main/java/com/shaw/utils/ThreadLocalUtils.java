package com.shaw.utils;

import java.util.*;

public class ThreadLocalUtils {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(4);
        }
    };

    public static Map<String, Object> getDataMap() {
        return THREAD_LOCAL.get();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        return (T) map.get(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defaultValue) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        T value = (T) map.get(key);
        return value == null ? defaultValue : value;
    }

    public static void put(String key, Object value) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        map.put(key, value);
    }

    public static boolean containsKey(String key) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        return map.containsKey(key);
    }

    public static void putAll(Map<String, Object> keyValueMap) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> fetchVarsByPrefix(String prefix) {
        Map<String, T> vars = new HashMap<>();
        if (prefix == null) {
            return vars;
        }
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        Set<Map.Entry<String, Object>> set = map.entrySet();

        for (Map.Entry<String, Object> entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    vars.put((String) key, (T) entry.getValue());
                }
            }
        }
        return vars;
    }

    @SuppressWarnings("unchecked")
    public static <T> T remove(String key) {
        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        return (T) map.remove(key);
    }

    public static void clear(String prefix) {
        if (prefix == null) {
            return;
        }

        Map<String, Object> map = (Map<String, Object>) THREAD_LOCAL.get();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        List<String> removeKeys = new ArrayList<>();
        for (Map.Entry<String, Object> entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    removeKeys.add((String) key);
                }
            }
        }

        for (String key : removeKeys) {
            map.remove(key);
        }
    }

}
