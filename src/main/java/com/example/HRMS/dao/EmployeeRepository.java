package com.example.HRMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HRMS.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
