package com.colak.controller.jpa;

import com.colak.model.jpa.Employee;
import com.colak.redis.config.RedisConfig;
import com.colak.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedisEmployeeController {

    public static final String CACHE_NAME = "employee";

    private final EmployeeService employeeService;

    // http://localhost:8080/redisfindbyid/1
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_NAME, cacheManager = RedisConfig.CACHE_MANAGER, key = "#userId")
    @GetMapping(path = "/redisfindbyid/{userId}")
    public Employee redisFindById(@PathVariable Long userId) {
        log.info("redisFindById is called with : {}", userId);
        return employeeService.findById(userId);
    }

    // http://localhost:8080/redisdeletebyid/1
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @GetMapping(path = "/redisdeletebyid/{userId}")
    public void redisDeleteUserByID(@PathVariable Long userId) {
        log.info("redisDeleteUserByID is called with : {}", userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
