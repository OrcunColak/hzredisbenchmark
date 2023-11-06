package com.colak.service;

import com.colak.model.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> findById(Long id);

    Mono<Boolean> save(Employee employee);
}
