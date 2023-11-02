package com.colak.service;

import com.colak.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Employee employee = new Employee(1L, "orcun", "colak");

    @Override
    public Employee findById(Long id) {
        return employee;
    }

}
