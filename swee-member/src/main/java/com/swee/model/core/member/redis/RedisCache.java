package com.swee.model.core.member.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**=========================String====================**/

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        }else {
            set(key, value);
        }
    }

    public Object get(String key) {
        return StringUtils.isNotBlank(key) ? redisTemplate.opsForValue().get(key) : null;
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void del(String... key) {
        if (key != null) {
            if (key.length == 1)
                redisTemplate.delete(key[0]);
            else
                redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0.");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0.");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //============Map=================//
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void hmset(String key,Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public void hmset(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    public void hset(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0)
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double incr) {
        return redisTemplate.opsForHash().increment(key, item, incr);
    }

    public double hdecr(String key, String item, double incr) {
        return redisTemplate.opsForHash().increment(key, item, -incr);
    }


    //===============set================//
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public long sSetAndTime(String key, long time, Object... values){
        long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0)
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return count;
    }

    public long sGetSetSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    //===============List================//
    public List<Object> lGet(String key,long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    public long lSet(String key, Object value) {
        return  redisTemplate.opsForList().rightPush(key, value);
    }

    public long lSet(String key, Object value, long time) {
        long count = redisTemplate.opsForList().rightPush(key, value);
        if (time > 0)
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return count;
    }

    public long lSet(String key, List<Object> values) {
        return  redisTemplate.opsForList().rightPushAll(key, values);
    }

    public long lSet(String key, List<Object> values, long time) {
        long count = redisTemplate.opsForList().rightPushAll(key, values);
        if (time > 0)
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return count;
    }

    public void lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key,index, value);
    }

    public long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key,count, value);
    }

}
