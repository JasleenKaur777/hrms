package com.example.HRMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HRMS.model.Project;
import java.util.List;
import com.example.HRMS.model.Employee;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
List<Project> findByEmployees(List<Employee> employees);
}
