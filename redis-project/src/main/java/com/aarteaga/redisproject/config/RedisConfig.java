package com.aarteaga.redisproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
@Slf4j
public class RedisConfig {

    @Value("${test}")
    private String hola;

    /*
    //local without password
    @Value("${redis.host}")
    private String redisHostName;

    @Value("${redis.port}")
    private Integer redisPort;

   @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHostName);
        redisConfig.setPort(redisPort);
        return new JedisConnectionFactory(redisConfig);
    }*/

    @Value("${redis.azure.host}")
    private String redisHostName;

    @Value("${redis.azure.port}")
    private Integer redisPort;

    @Value("${redis.azure.key}")
    private String key;

    /*
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        log.info("Hostname: " + redisHostName);
        log.info("Port: " + redisPort);
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHostName);
        redisConfig.setPort(redisPort);
        redisConfig.setPassword("abc");
        return new JedisConnectionFactory(redisConfig);
    }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        log.info("Hostname: " + redisHostName);
        log.info("Port: " + redisPort);
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHostName);
        redisConfig.setPort(redisPort);
        redisConfig.setPassword(key);

        boolean useSsl = true;
        JedisClientConfiguration jedisConfig;
        if (useSsl) {
            jedisConfig = JedisClientConfiguration
                    .builder()
                    .useSsl()
                    .build();
        } else {
            jedisConfig = JedisClientConfiguration
                    .builder()
                    .build();
        }
        JedisConnectionFactory jcf = new JedisConnectionFactory(redisConfig, jedisConfig);
        return jcf;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        //redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //redisTemplate.setEnableTransactionSupport(true);
        //redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
