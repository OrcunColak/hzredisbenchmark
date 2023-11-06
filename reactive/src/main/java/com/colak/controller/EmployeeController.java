package com.colak.controller;

import com.colak.model.Employee;
import com.colak.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostConstruct
    void postConstruct() {
        employeeService.save(new Employee(1L, "employee1", "lastname1"))
                .subscribe(result -> log.info("Save result {}", result));

        employeeService.save(new Employee(2L, "employee2", "lastname2"))
                .subscribe(result -> log.info("Save result {}", result));
    }

    // http:localhost:8080/findbyid/1
    @GetMapping(path = "/findbyid/{userId}")
    public Mono<Employee> findById(@PathVariable Long userId) {
        return employeeService.findById(userId);
    }
}
