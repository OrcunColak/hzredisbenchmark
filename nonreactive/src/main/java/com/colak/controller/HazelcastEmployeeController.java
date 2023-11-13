package com.colak.controller;

import com.colak.hazelcast.config.HazelcastConfig;
import com.colak.model.Employee;
import com.colak.service.EmployeeService;
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
public class HazelcastEmployeeController {

    public static final String CACHE_NAME = "employee";

    private final EmployeeService employeeService;

    // http://localhost:8080/hazelcastfindbyid/1
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_NAME, cacheManager = HazelcastConfig.CACHE_MANAGER, key = "#userId")
    @GetMapping(path = "/hazelcastfindbyid/{userId}")
    public Employee hazelcastFindById(@PathVariable Long userId) {
        log.info("hazelcastfindbyid is called with : {}", userId);
        return employeeService.findById(userId);
    }

    // http://localhost:8080/deletebyid/1
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @GetMapping(path = "/hazelcastdeletebyid/{userId}")
    public void hazelcastDeleteUserByID(@PathVariable Long userId) {
        log.info("hazelcastDeleteUserByID is called with : {}", userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
