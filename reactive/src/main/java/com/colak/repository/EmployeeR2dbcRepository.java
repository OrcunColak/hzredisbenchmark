package com.colak.repository;

import com.colak.model.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeR2dbcRepository extends R2dbcRepository<Employee, Long> {

}
