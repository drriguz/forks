package com.riguz.gags.io;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.yaml.snakeyaml.Yaml;

public final class Config {
    private static final ConcurrentMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

    private Config() {

    }

    @SuppressWarnings("unchecked")
    public static void cache(InputStream f) {
        Yaml yaml = new Yaml();
        Map<String, Object> keys = (Map<String, Object>) yaml.load(f);
        cache.putAll(keys);
    }
    
    public static void cache(String fileName){
        cache(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T get(String key){
        return (T) cache.get(key);
    }

    public static <T> Map<String, Object> use(String fileName){
        
        return use(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> use(InputStream f) {
        Yaml yaml = new Yaml();
        return (Map<String, Object>) yaml.load(f);
    }

    public static void clearCache() {
        cache.clear();
    }
}
