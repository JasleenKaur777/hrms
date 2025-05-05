package com.example.HRMS.service;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.HRMS.DTO.EmployeeDTO;
import com.example.HRMS.DTO.ProjectDTO;
import com.example.HRMS.dao.DepartmentRepository;
import com.example.HRMS.dao.EmployeeRepository;
import com.example.HRMS.dao.ProjectRepository;
import com.example.HRMS.exception.ResourceNotFoundException;
import com.example.HRMS.model.Department;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository emprepo;
	@Autowired
	ProjectRepository projectrepo;
	
	@Autowired
	DepartmentRepository deptrepo;
	
	@Autowired
	ModelMapper mapper;

	public EmployeeDTO insertEmployeeWithProjects(EmployeeDTO employee, Long departmentId, List<Long> projectIds) {
	    Department dept = deptrepo.findById(departmentId)
	        .orElseThrow(() -> new ResourceNotFoundException("Department", "Department ID", departmentId));

	    List<Project> projects = projectrepo.findAllById(projectIds);
	    if (projects.size() != projectIds.size()) {
	        throw new ResourceNotFoundException("Project", "Project id", projectIds);
	    }

	    Employee emp = new Employee();
	    emp.setName(employee.getName());
	    emp.setEmail(employee.getEmail());
	    emp.setPosition(employee.getPosition());
	    emp.setDepartment(dept);
	    emp.setProjectlist(projects);

	    Employee saved = emprepo.save(emp);
	    return mapper.map(saved, EmployeeDTO.class);
	}
	public List<EmployeeDTO> getEmployee() {
		List<Employee> employees=emprepo.findAll();
		List<EmployeeDTO> dtos=employees.stream().map(emp->mapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		return dtos;
	}
	public EmployeeDTO getEmployeeById(Long id) {
	    Employee employee = emprepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
	    return mapper.map(employee, EmployeeDTO.class);
	}

	public EmployeeDTO updateEmployee(Long id, Long deptId, List<Long> projectIds, EmployeeDTO dto) {
        Employee employee = emprepo.findById(id).orElseThrow();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPosition(dto.getPosition());

        Department department = deptrepo.findById(deptId).orElseThrow();
        employee.setDepartment(department);

        List<Project> projects = projectrepo.findAllById(projectIds);
        employee.setProjects(projects);

        return mapper.map(emprepo.save(employee), EmployeeDTO.class);
    }

    public void deleteEmployee(Long id) {
        emprepo.deleteById(id);
    }

    public EmployeeDTO assignProjects(Long id, List<Long> projectIds) {
        Employee employee = emprepo.findById(id).orElseThrow();
        List<Project> projects = projectrepo.findAllById(projectIds);
        employee.setProjects(projects);
        return mapper.map(emprepo.save(employee), EmployeeDTO.class);
    }

    public EmployeeDTO assignDepartment(Long id, Long deptId) {
        Employee employee = emprepo.findById(id).orElseThrow();
        Department department = deptrepo.findById(deptId).orElseThrow();
        employee.setDepartment(department);
        return mapper.map(emprepo.save(employee), EmployeeDTO.class);
    }

    public List<EmployeeDTO> getEmployeesByProject(Long projectId) {
        Project project = projectrepo.findById(projectId).orElseThrow();
        return project.getEmployees().stream().map(e -> mapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByDepartment(Long deptId) {
        Department dept = deptrepo.findById(deptId).orElseThrow();
        return dept.getEmployees().stream().map(e -> mapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
