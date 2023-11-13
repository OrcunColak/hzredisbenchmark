package com.colak.hazelcast.config;

import com.colak.config.ProfileNames;
import com.hazelcast.client.config.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(ProfileNames.HZ_PROFILE)
public class HazelcastConfig {

    // org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration only creates a
    // com.hazelcast.spring.cache.HazelcastCacheManager instance.
    // It does not read any configuration from application.properties
    @Bean
    public ClientConfig hazelCastConfig() {
        return new ClientConfig();
    }
}
