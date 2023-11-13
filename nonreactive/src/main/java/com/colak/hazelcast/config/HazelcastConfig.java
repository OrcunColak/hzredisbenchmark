package com.colak.hazelcast.config;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    public static final String CACHE_MANAGER = "hazelcastCacheManager";

    // org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration creates a
    // com.hazelcast.spring.cache.HazelcastCacheManager instance.
    // It does not read any configuration from application.properties
    @Bean
    public ClientConfig hazelCastConfig() {
        return new ClientConfig();
    }

    @Bean
    public HazelcastCacheManager hazelcastCacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }
}
