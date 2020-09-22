package com.swee.model.core.member.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Log4j2
public class JedisConfig extends CachingConfigurerSupport {

    @Bean
    public JedisPool redisPoolFactory(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.timeout}") Duration timeout,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.jedis.pool.max-wait}") Duration maxWait,
            @Value("${spring.redis.jedis.pool.max-active}")int maxActive,
            @Value("${spring.redis.jedis.pool.max-idle}")int maxIdle,
            @Value("${spring.redis.jedis.pool.min-idle}")int minIdle) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait.toMillis());
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,(int)timeout.toMillis(),password);

        log.info("JedisPool注入成功！");
        log.info("redis地址：" + host + ":" + port);
        return  jedisPool;

    }
}
