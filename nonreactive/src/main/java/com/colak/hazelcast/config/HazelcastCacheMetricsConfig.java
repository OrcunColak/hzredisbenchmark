package com.colak.hazelcast.config;

import com.colak.controller.jpa.HazelcastEmployeeController;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class HazelcastCacheMetricsConfig {

    private final HazelcastCacheManager hazelcastCacheManager;

    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    // https://stackoverflow.com/questions/59142947/unable-to-look-at-cache-metricshit-miss-size-in-spring-boot
    @EventListener(ApplicationStartedEvent.class)
    public void addCachesMetrics() {
        Cache hazelcastCache = hazelcastCacheManager.getCache(HazelcastEmployeeController.CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(hazelcastCache);
    }
}
