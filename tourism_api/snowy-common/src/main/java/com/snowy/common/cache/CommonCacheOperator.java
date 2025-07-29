
package com.snowy.common.cache;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 通用Redis缓存操作器
 *
 * @author snowy
 *
 **/
@Component
public class CommonCacheOperator {

    /** 所有缓存Key的前缀 */
    private static final String CACHE_KEY_PREFIX = "Cache:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void put(String key, Object value) {
        redisTemplate.boundValueOps(CACHE_KEY_PREFIX + key).set(value);
    }

    public void put(String key, Object value, long timeoutSeconds) {
        redisTemplate.boundValueOps(CACHE_KEY_PREFIX + key).set(value, timeoutSeconds, TimeUnit.SECONDS);
    }



    public Object get(String key) {
        return redisTemplate.boundValueOps(CACHE_KEY_PREFIX + key).get();
    }



    public void remove(String... key) {
        ArrayList<String> keys = CollectionUtil.toList(key);
        List<String> withPrefixKeys = keys.stream().map(i -> CACHE_KEY_PREFIX + i).collect(Collectors.toList());
        redisTemplate.delete(withPrefixKeys);
    }

    public Collection<String> getAllKeys() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (keys != null) {
            // 去掉缓存key的common prefix前缀
            return keys.stream().map(key -> StrUtil.removePrefix(key, CACHE_KEY_PREFIX)).collect(Collectors.toSet());
        } else {
            return CollectionUtil.newHashSet();
        }
    }

    public Collection<Object> getAllValues() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (keys != null) {
            return redisTemplate.opsForValue().multiGet(keys);
        } else {
            return CollectionUtil.newArrayList();
        }
    }

    public Map<String, Object> getAllKeyValues() {
        Collection<String> allKeys = this.getAllKeys();
        HashMap<String, Object> results = MapUtil.newHashMap();
        for (String key : allKeys) {
            results.put(key, this.get(key));
        }
        return results;
    }

    public void removeBatch(String pattern) {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + pattern);
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
