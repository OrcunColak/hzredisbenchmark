package com.colak.service.employee;

import com.colak.model.jpa.Employee;
import com.colak.repository.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Long id) {
        // may throw java.util.NoSuchElementException
        return employeeRepository.findById(id).orElseThrow();
    }

}
