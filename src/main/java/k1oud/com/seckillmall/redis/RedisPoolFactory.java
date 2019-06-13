package k1oud.com.seckillmall.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//我操，难道是因为我没有加@Service注解，所以一直没有把这个bean加入spring
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;
    //不要忘了这个注解，不然jedisPool无法加入到spring容器中进行管理
    @Bean
    public JedisPool jedisPoolFactory() {
        //通过JedisPoolConfig生成
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,
                redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000, redisConfig.getPassword());

        return jedisPool;
    }
}
