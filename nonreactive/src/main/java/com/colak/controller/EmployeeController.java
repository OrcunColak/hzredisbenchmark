package com.colak.controller;

import com.colak.model.Employee;
import com.colak.service.EmployeeService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class EmployeeController {

    private final EmployeeService employeeService;


    // http://localhost:8080/nocachefindbyid/1
    // http://localhost:8080/actuator/metrics/nocachefindbyid
    @Timed("findbyidnocache")
    @Transactional(readOnly = true)
    @GetMapping(path = "/nocachefindbyid/{userId}")
    public Employee nocachefindbyid(@PathVariable Long userId) {
        log.info("nocachefindbyid is called with : {}", userId);
        return employeeService.findById(userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
