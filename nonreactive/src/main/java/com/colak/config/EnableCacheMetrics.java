package com.colak.config;

import com.colak.controller.EmployeeController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class EnableCacheMetrics {

    private final CacheManager cacheManager;

    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    @EventListener(ApplicationStartedEvent.class)
    public void addCachesMetrics() {
        // https://stackoverflow.com/questions/59142947/unable-to-look-at-cache-metricshit-miss-size-in-spring-boot
        Cache cache = cacheManager.getCache(EmployeeController.CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(cache);
    }
}
