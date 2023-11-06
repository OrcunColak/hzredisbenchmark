package com.colak.repository;

import com.colak.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class EmployeeRedisRepository {

    private final ReactiveRedisTemplate<String, Employee> reactiveRedisTemplate;

    private static final String EMPLOYEE_PREFIX_KEY = "employee_reactive:";

    public Mono<Employee> findByIdForHash(Long id) {
        String key = String.valueOf(id);
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.get(EMPLOYEE_PREFIX_KEY, key);
    }

    public Mono<Employee> saveForHash(Employee employee) {
        String key = String.valueOf(employee.getId());
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.put(EMPLOYEE_PREFIX_KEY, key, employee).thenReturn(employee);
    }

    public Mono<Long> deleteByIdForHash(Long id) {
        String key = String.valueOf(id);
        ReactiveHashOperations<String, String, Employee> hashOperations = getOpsForHash();
        return hashOperations.remove(EMPLOYEE_PREFIX_KEY, key);
    }

    public Mono<Employee> findByIdForValue(Long id) {
        String key = EMPLOYEE_PREFIX_KEY + id;
        ReactiveValueOperations<String, Employee> valueOperations = getOpsForValue();
        return valueOperations.get(key);
    }

    public Mono<Employee> saveForValue(Employee employee) {
        String key = EMPLOYEE_PREFIX_KEY + employee.getId();
        ReactiveValueOperations<String, Employee> valueOperations = getOpsForValue();
        return valueOperations.set(key, employee).thenReturn(employee);
    }

    private ReactiveHashOperations<String, String, Employee> getOpsForHash() {
        return reactiveRedisTemplate.opsForHash();
    }

    private ReactiveValueOperations<String, Employee> getOpsForValue() {
        return reactiveRedisTemplate.opsForValue();
    }

}
