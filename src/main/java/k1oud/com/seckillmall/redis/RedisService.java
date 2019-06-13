package k1oud.com.seckillmall.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    public <T> T redisGet(KeyPrefix keyPrefix,String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix()+key;
            String value = jedis.get(realKey);
            T t = stringToBean(value, clazz);
            return t;
        } catch (Exception e) {
            //待处理方法
        } finally {
            returnToPool(jedis);
        }
        return null;
    }

    public <T> T redisSet(KeyPrefix keyPrefix,String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String bean = beanToString(value);
            String realKey = keyPrefix.getPrefix()+key;
            jedis.set(realKey, bean);
        } catch (Exception e) {
            //待处理方法
        } finally {
            returnToPool(jedis);
        }
        return null;
    }

    //这个方法看名字就知道，把任意类型转换成字符串存在redis中
    private <T> String beanToString(T bean) {
        //需要判断排除一些特殊类型
        if (bean == null) {
            return null;
        }
        Class<?> clazz = bean.getClass();
        if (clazz == int.class || clazz == Integer.class || clazz == long.class || clazz == Long.class) {
            return "" + bean;
        } else if (clazz == String.class) {
            return (String) bean;
        } else {
            return JSON.toJSONString(bean);
        }

    }

    private <T> T stringToBean(String value, Class<T> clazz) {
        if (value == null || value.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(value);
        } else if (clazz == String.class) {
            return (T) value;
        } else {
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    //2019年6月13日，晚上终于搞定了github账号，别tm再瞎B操作了

}
