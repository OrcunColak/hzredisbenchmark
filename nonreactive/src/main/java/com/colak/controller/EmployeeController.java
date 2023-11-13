package com.colak.controller;

import com.colak.model.Employee;
import com.colak.service.EmployeeService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;


    // http://localhost:8080/findbyidnocache/1
    // http://localhost:8080/actuator/metrics/findbyidnocache
    @Timed("findbyidnocache")
    @GetMapping(path = "/findbyidnocache/{userId}")
    public Employee findByIdNoCache(@PathVariable Long userId) {
        log.info("findById is called with : {}", userId);
        return employeeService.findById(userId);
    }
}
