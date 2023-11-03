package com.colak.service;

import com.colak.model.Employee;
import com.colak.repository.EmployeeRepository;
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
