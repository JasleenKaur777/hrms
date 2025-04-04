package com.example.HRMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HRMS.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
