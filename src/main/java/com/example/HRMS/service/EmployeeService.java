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
import com.example.HRMS.DTO.EmployeeProject;
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

	public EmployeeDTO insertEmpl(EmployeeDTO employee , Long departmentid, Long projectid) {
	    Department dept = deptrepo.findById(departmentid)
	        .orElseThrow(() -> new ResourceNotFoundException("Department", "Department id", departmentid));

	    Project project = projectrepo.findById(projectid)
	        .orElseThrow(() -> new ResourceNotFoundException("Project", "projectid", projectid));

	    Employee emp = new Employee();
	    emp.setDepartment(dept);
	    emp.setEmail(employee.getEmail());
	    emp.setName(employee.getName());
	    emp.setPosition(employee.getPosition());

	    // ✅ Use the actual Project entity directly
	    List<Project> projectList = new ArrayList<>();
	    projectList.add(project);
	    emp.setProjectlist(projectList);

	    Employee savedEmp = emprepo.save(emp);

	    // ✅ Ensure ModelMapper handles nested mapping
	    return mapper.map(savedEmp, EmployeeDTO.class);
	}
	public EmployeeDTO createEmpl(EmployeeDTO employee ) {
	   

	    Employee emp = new Employee();
	   
	    emp.setEmail(employee.getEmail());
	    emp.setName(employee.getName());
	    emp.setPosition(employee.getPosition());

	  

	    Employee savedEmp = emprepo.save(emp);

	    // ✅ Ensure ModelMapper handles nested mapping
	    return mapper.map(savedEmp, EmployeeDTO.class);
	}


//	public ResponseEntity<String> insertEmployeeProject(Long id, List<Long> projectIds) {
//		Optional<Employee> empOpt = emprepo.findById(id);
//
//		if (!empOpt.isPresent()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
//		}
//
//		Employee employee = empOpt.get();
//		List<Project> projectList = projectrepo.findAllById(projectIds);
//		Set<Project> projectset=new HashSet<Project>(projectList);
//
//		if (projectset.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid projects found for the given IDs.");
//		}
//		employee.getProjectlist().addAll(projectset);
//
//		emprepo.save(employee);
//
//		return ResponseEntity.status(HttpStatus.OK).body("Projects successfully assigned to Employee with ID: " + id);
//	}
//
	public List<EmployeeDTO> getEmployee() {
		List<Employee> employees=emprepo.findAll();
		List<EmployeeDTO> dtos=employees.stream().map(emp->mapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		return dtos;
	}
//
//	public Employee updateEmployee(Employee employee) {
//		Optional<Employee> empopt = emprepo.findById(employee.getId());
//		if (empopt.isPresent()) {
//			Employee emp = empopt.get();
//			emp.setId(employee.getId());
//			emp.setName(employee.getName());
//			emp.setEmail(employee.getEmail());
//			emp.setPosition(employee.getPosition());
//			emprepo.save(emp);
//			return emp;
//		} else {
//			return null;
//		}
//
//	}
//
//	public ResponseEntity<String> deleteEmployee(Long id) {
//		Optional<Employee> empopt = emprepo.findById(id);
//		if (empopt.isPresent()) {
//			Employee emp = empopt.get();
//			emprepo.delete(emp);
//
//			return new ResponseEntity<String>("Employee is deleted", HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<String>("Employee is not deleted", HttpStatus.NOT_FOUND);
//	}
//
//	public EmployeeProject getEmployeeProject(Long id) {
//          Optional<Employee> empopt=  emprepo.findById(id);
//          if(empopt.isPresent()) {
//        	Employee emp=  empopt.get();
//        	EmployeeProject emp_project=new EmployeeProject(emp);
//        	return emp_project;
//          }
//          else {
//        	  return null;
//          }
//	}

}
