package com.colak.hazelcast.config;

import com.hazelcast.client.config.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hazelcast")
public class HazelcastConfig {

    @Bean
    public ClientConfig hazelCastConfig() {
        ClientConfig clientConfig = new ClientConfig();
        return clientConfig;
    }
}
