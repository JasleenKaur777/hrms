package com.example.HRMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HRMS.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
