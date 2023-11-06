package com.colak.controller;

import com.colak.model.Employee;
import com.colak.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    // http:localhost:8080/findbyid/1
    @Cacheable(cacheNames = "employee", key = "#userId")
    @GetMapping(path = "/findbyid/{userId}")
    public Employee findById(@PathVariable Long userId) {
        log.info("findById is called with : {}", userId);
        return employeeService.findById(userId);
    }

    // http:localhost:8080/deletebyid/1
    @CacheEvict(value = "employee", allEntries = true)
    @GetMapping(path = "/deletebyid/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        log.info("deletebyid is called with : {}", userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
