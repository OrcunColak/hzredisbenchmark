package com.colak.config;

import com.colak.controller.HazelcastEmployeeController;
import com.colak.controller.RedisEmployeeController;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
@RequiredArgsConstructor
public class EnableCacheMetrics {

    private final RedisCacheManager redisCacheManager;
    private final HazelcastCacheManager hazelcastCacheManager;

    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    @EventListener(ApplicationStartedEvent.class)
    public void addCachesMetrics() {
        // https://stackoverflow.com/questions/59142947/unable-to-look-at-cache-metricshit-miss-size-in-spring-boot
        Cache redisCache = redisCacheManager.getCache(RedisEmployeeController.CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(redisCache);

        Cache hazelcastCache = hazelcastCacheManager.getCache(HazelcastEmployeeController.CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(hazelcastCache);
    }
}
