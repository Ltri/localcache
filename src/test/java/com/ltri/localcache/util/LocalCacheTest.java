package com.ltri.localcache.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


class LocalCacheTest {

    @Test
    void set() {
        LocalCache.set("testKey", "testKey");
        System.out.println(LocalCache.get("testKey"));
    }

    @Test
    void testSet() throws InterruptedException {
        LocalCache.set("test", "test", 3, TimeUnit.SECONDS);
        System.out.println(LocalCache.get("test"));
        Thread.sleep(4000);
        System.out.println(LocalCache.get("test"));
    }

    @Test
    void setAll() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("test1", "test1");
        map.put("test2", "test2");
        map.put("test3", "test3");
        map.put("test4", "test4");
        LocalCache.setAll(map, 3, TimeUnit.SECONDS);
        System.out.println(LocalCache.get("test1"));
        System.out.println(LocalCache.get("test2"));
        System.out.println(LocalCache.get("test3"));
        System.out.println(LocalCache.get("test4"));
        Thread.sleep(4000);
        System.out.println(LocalCache.get("test1"));
        System.out.println(LocalCache.get("test2"));
        System.out.println(LocalCache.get("test3"));
        System.out.println(LocalCache.get("test4"));
    }

    @Test
    void get() {
        System.out.println(LocalCache.get("testKey"));
    }

    @Test
    void containsKey() {
        System.out.println(LocalCache.containsKey("testKey"));
    }

    @Test
    void remove() {
        LocalCache.remove("testKey");
    }


    @Test
    void size() {
        System.out.println(LocalCache.size());
    }

    @Test
    void clear() {
        LocalCache.clear();
    }
}