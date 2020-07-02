package com.ltri.localcache.util;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Ltri
 * @date 2020/7/2 1:01 下午
 */
public class LocalCache {
    /**
     * 默认有效时长,单位:毫秒
     */
    private static final int DEFAULT_TIMEOUT = 3600 * 1000;

    private static final Map<String, Object> map;

    private static final Timer timer;

    /**
     * 私有构造函数,工具类不允许实例化
     */
    private LocalCache() {

    }

    static {
        timer = new Timer();
        map = new ConcurrentHashMap<>();
    }


    /**
     * 清除缓存任务类
     */
    static class DeleteKeyTask extends TimerTask {

        private String key;

        public DeleteKeyTask(String key) {
            this.key = key;
        }

        public void run() {
            LocalCache.remove(key);
        }
    }

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        map.put(key, value);
        timer.schedule(new DeleteKeyTask(key), DEFAULT_TIMEOUT);
    }


    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param timeout  有效时长
     * @param timeUnit 单位
     */
    public static void set(String key, Object value, int timeout, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(timeout);
        map.put(key, value);
        timer.schedule(new DeleteKeyTask(key), millis);
    }


    /**
     * 批量增加缓存
     *
     * @param maps
     * @param timeout  有效时长
     * @param timeUnit 单位
     */
    public static void setAll(Map<String, Object> maps, int timeout, TimeUnit timeUnit) {
        map.putAll(maps);
        long millis = timeUnit.toMillis(timeout);
        for (String key : maps.keySet()) {
            timer.schedule(new DeleteKeyTask(key), millis);
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return map.get(key);
    }

    /**
     * 查询缓存是否包含key
     *
     * @param key
     * @return
     */
    public static boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        map.remove(key);
    }

    /**
     * 删除缓存
     *
     * @param o
     */
    public static void remove(Object o) {
        map.remove(o);
    }

    /**
     * 返回缓存大小
     *
     * @return
     */
    public static int size() {
        return map.size();
    }

    /**
     * 清除所有缓存
     *
     * @return
     */
    public static void clear() {
        if (size() > 0) {
            map.clear();
        }
        timer.cancel();
    }

}
