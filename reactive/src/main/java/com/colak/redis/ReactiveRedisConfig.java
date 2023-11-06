package com.colak.redis;

import com.colak.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReactiveRedisConfig {

//    @Bean
//    public ReactiveRedisTemplate reactiveRedisTemplate(
//            ReactiveRedisConnectionFactory connectionFactory) {
//        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext.newSerializationContext();
//
//        // Key serializer
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        builder.key(stringRedisSerializer);
//        builder.hashKey(stringRedisSerializer);
//
//        // Value serializer
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        builder.value(genericJackson2JsonRedisSerializer);
//        builder.hashValue(genericJackson2JsonRedisSerializer);
//        ReactiveRedisTemplate reactiveRedisTemplate = new ReactiveRedisTemplate(connectionFactory, builder.build());
//        return reactiveRedisTemplate;
//    }

    @Bean
    public ReactiveRedisTemplate<String, Employee> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {

        RedisSerializationContext.RedisSerializationContextBuilder<String, Employee> builder = RedisSerializationContext
                .newSerializationContext();

        // Key serializer
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        builder.key(keySerializer).hashKey(keySerializer);

        // Value serializer can be
        // JdkSerializationRedisSerializer,
        // GenericJackson2JsonRedisSerializer,
        // Jackson2JsonRedisSerializer<Employee>
        Jackson2JsonRedisSerializer<Employee> valueSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        RedisSerializationContext<String, Employee> context = builder.value(valueSerializer)
                .hashValue(valueSerializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
