package com.colak.service;

import com.colak.model.Employee;
import com.colak.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> findById(Long id) {
        Mono<Employee> byId = employeeRepository.findById(id);
        return byId
                .defaultIfEmpty(new Employee(-1L, "empty", "empty"));
    }

    @Override
    public Mono<Boolean> save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
