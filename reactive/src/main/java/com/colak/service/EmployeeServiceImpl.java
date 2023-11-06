package com.colak.service;

import com.colak.model.Employee;
import com.colak.repository.EmployeeR2dbcRepository;
import com.colak.repository.EmployeeRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRedisRepository employeeRedisRepository;


    private final EmployeeR2dbcRepository employeeRepository;

    @Override
    public Mono<Employee> findById(Long id) {
        // See https://gist.github.com/marttp/43e3051768bc29d02c8b72e5010d1de2
        Mono<Employee> redisMono = employeeRedisRepository.findByIdForValue(id);
        Mono<Employee> databaseMono = employeeRepository.findById(id)
                .flatMap(employee -> employeeRedisRepository.saveForValue(employee).thenReturn(employee));

        return redisMono
                .switchIfEmpty(databaseMono)
                .defaultIfEmpty(new Employee(-1L, "empty", "empty"));
    }

    @Override
    public Mono<Employee> save(Employee employee) {
        return employeeRedisRepository.saveForValue(employee);
    }
}
