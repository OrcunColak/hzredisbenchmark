package com.colak.controller;

import com.colak.model.Employee;
import com.colak.model.QEmployee;
import com.colak.service.EmployeeService;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    private final JPAQueryFactory jpaQueryFactory;


    // http://localhost:8080/springNocacheFindById/1
    // http://localhost:8080/actuator/metrics/springNocacheFindById
    @Timed("findbyidnocache")
    @Transactional(readOnly = true)
    @GetMapping(path = "/springnocachefindbyid/{userId}")
    public Employee springNocacheFindById(@PathVariable Long userId) {
        log.info("springNocacheFindById is called with : {}", userId);
        return employeeService.findById(userId);
    }

    // http://localhost:8080/queryDslNocacheFindById/1
    @GetMapping(path = "/queryDslNocacheFindById/{userId}")
    public Employee queryDslNocacheFindById(@PathVariable Long userId) {
        log.info("queryDslNocacheFindById is called with : {}", userId);
        QEmployee qEmployee = QEmployee.employee;
        Employee employee = jpaQueryFactory
                .selectFrom(qEmployee)
                .where(qEmployee.id.eq(userId))
                .fetchOne();
        if (employee == null) {
            throw new NoSuchElementException();
        }
        return employee;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
