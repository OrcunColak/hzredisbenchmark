package com.colak.service.employee;

import com.colak.model.jpa.Employee;

public interface EmployeeService {
    Employee findById(Long id);
}
