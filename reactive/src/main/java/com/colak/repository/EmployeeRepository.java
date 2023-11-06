package com.colak.repository;

import com.colak.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class EmployeeRepository {

    private final ReactiveRedisTemplate<String, Employee> reactiveRedisTemplate;

    private static final String EMPLOYEE_PREFIX_KEY = "employee_reactive:";

    public Mono<Employee> findById(Long id) {
        String key = String.valueOf(id);
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.get(EMPLOYEE_PREFIX_KEY, key);
    }

    public Mono<Boolean> save(Employee employee) {
        String key = String.valueOf(employee.getId());
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.put(EMPLOYEE_PREFIX_KEY, key, employee);
    }

    public Mono<Long> deleteById(Long id) {
        String key = String.valueOf(id);
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.remove(EMPLOYEE_PREFIX_KEY, key);
    }

    private ReactiveHashOperations<String, String, Employee> getOpsForHash() {
        return reactiveRedisTemplate.opsForHash();
    }

}
