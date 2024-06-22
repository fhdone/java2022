package com.fhdone.java2022.august.utils;

import java.util.*;
import java.util.concurrent.*;

// https://juejin.cn/post/6987433876880621598
public class ExpireCache<T> {

    private ConcurrentMap<String,DelayValue<T> > map = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static final long DEFAULT_PERIOD_SECOND = 30;

    /**
     * 创建默认新过期缓存
     *
     * @see #ExpireCache(long, TimeUnit)
     */
    public ExpireCache() {
        this(DEFAULT_PERIOD_SECOND, TimeUnit.SECONDS);
    }

    /**
     * 创建新过期缓存
     * @param period 每两次扫描相隔时间
     * @param timeUnit 时间单位
     */
    public ExpireCache(long period, TimeUnit timeUnit) {
        // 创建缓存时，启动定时扫描周期任务
        executor.scheduleAtFixedRate(this::scanClean, period, period, timeUnit);
    }

    /**
     * 放入一个不过期 k v
     * @param key
     * @param value
     */
    public void put(String key, T value){
        map.put(key, new DelayValue<>(value));
    }

    /**
     * 放入一个可过期 k v
     * @param key 键
     * @param value 值
     * @param delay 过期时间 单位毫秒
     */
    public void put(String key, T value, long delay){
        map.put(key, new DelayValue<>(value, delay));
    }

    /**
     * 取值的适合判断值是否过期
     * @param key
     * @return 值或null（如果key不存在或过期的话）
     */
    public T get(String key){
        DelayValue<T> v = map.get(key);
        if(Objects.isNull(v)){
            return null;
        }
        if(expired(v)){
            map.remove(key);
            return null;
        }
        return v.data;
    }


    /**
     * 随机扫描20个可过期 key 判断删除
     */
    private void scanClean(){

        List<String> canExpiredKeys = new ArrayList<>();
        for (Map.Entry<String, DelayValue<T>> entry : map.entrySet()) {
            if(canExpired(entry.getValue())){
                canExpiredKeys.add(entry.getKey());
            }
        }

        Collections.shuffle(canExpiredKeys);
        for (int i = 0, end = Math.min(canExpiredKeys.size(), 20); i < end; i++) {
            String key = canExpiredKeys.get(i);
            DelayValue<T> v = map.get(key);
            if (Objects.nonNull(v) && expired(v)) {
                map.remove(key);
            }
        }
    }

    private boolean canExpired(DelayValue<T> v){
        Objects.requireNonNull(v);
        return v.delay != DelayValue.FOREVER_FLAG;
    }

    private boolean expired(DelayValue<T> v){
        Objects.requireNonNull(v);
        return v.delay == DelayValue.FOREVER_FLAG ? false :
                (System.currentTimeMillis() > v.delay + v.timestamp);
    }


    private static class DelayValue<T> {

        static final long FOREVER_FLAG = -1;

        final T data;
        final long timestamp = System.currentTimeMillis();
        final long delay;  // -1表示永不过期

        DelayValue(T data){
            this.data = data;
            this.delay = FOREVER_FLAG;
        }

        DelayValue(T data, long delay){
            this.data = data;
            this.delay = delay;
        }

        @Override
        public String toString() {
            return "DelayValue [data=" + data + ", delay=" + delay + ", timestamp=" + timestamp + "]";
        }
    }
}
