package com.sipc.intelligentfarmbackend.utils;
;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    public <T> void setValue(String key, T value) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setValue(String key, T value, long timeout, TimeUnit unit) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (timeout > 0) {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public void deleteValue(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        redisTemplate.delete(key);
    }

    public <T> Optional<T> getValue(String key, Class<T> clazz) {
        Object o = redisTemplate.opsForValue().get(key);
        if (clazz.isInstance(o)) {
            return Optional.of(clazz.cast(o));
        }
        return Optional.empty();
    }

    /**
     * Get a string value.
     *
     * @param key the cache key
     * @return Optional containing the string value, or empty if not found or if the key is invalid.
     */
    public Optional<String> getString(String key) {
        if (key == null || key.isEmpty()) {
            return Optional.empty();
        }
        Object value = redisTemplate.opsForValue().get(key);
        if (value instanceof String) {
            return Optional.of((String) value);
        }
        return Optional.empty();
    }

    /**
     * Set a list into the cache for any type of objects.
     *
     * @param key  the cache key
     * @param list the list to cache
     * @param <T>  the type of elements in the list
     */
    public <T> void setList(String key, List<T> list, long timeout, TimeUnit unit) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        if (list.isEmpty()) {
            redisTemplate.delete(key);
        } else {
            redisTemplate.opsForList().rightPushAll(key, list.toArray());
            redisTemplate.expire(key, timeout, unit);
        }
    }


    /**
     * Set a list into the cache for any type of objects.
     *
     * @param key  the cache key
     * @param list the list to cache
     * @param <T>  the type of elements in the list
     */
    public <T> void setList(String key, List<T> list) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        if (list.isEmpty()) {
            redisTemplate.delete(key);
        } else {
            redisTemplate.opsForList().rightPushAll(key, list.toArray());
        }
    }

    /**
     * Get a list from the cache.
     *
     * @param key   the cache key
     * @param clazz the type of List elements
     * @return Optional containing the cached list, or an empty Optional if the list is not found
     */
    public <T> Optional<List<T>> getList(String key, Class<T> clazz) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        List<Object> rawList = redisTemplate.opsForList().range(key, 0, -1);
        if (rawList == null || rawList.isEmpty()) {
            return Optional.empty();
        }
        List<T> typedList = rawList.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
        return Optional.of(typedList);
    }

    /**
     * Check if a key exists in the cache.
     *
     * @param key the cache key
     * @return true if the key exists, false otherwise
     * @throws IllegalArgumentException if key is null or empty
     */
    public boolean hasKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        Boolean result = redisTemplate.hasKey(key);
        return result != null && result;
    }


}




