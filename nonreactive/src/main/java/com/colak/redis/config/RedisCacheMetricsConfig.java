package com.colak.redis.config;

import com.colak.controller.jpa.RedisEmployeeController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
@RequiredArgsConstructor
public class RedisCacheMetricsConfig {

    private final RedisCacheManager redisCacheManager;


    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    // https://stackoverflow.com/questions/59142947/unable-to-look-at-cache-metricshit-miss-size-in-spring-boot
    @EventListener(ApplicationStartedEvent.class)
    public void addCachesMetrics() {
        Cache redisCache = redisCacheManager.getCache(RedisEmployeeController.CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(redisCache);
    }
}
