package com.example.HRMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.HRMS.DTO.EmployeeProject;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;
import com.example.HRMS.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@PostMapping("/addempl")
	public ResponseEntity<String> insertEmpl(@RequestBody Employee employee) {
		service.insertEmpl(employee);
		return new ResponseEntity<String>("Employee is inserted",HttpStatus.OK);
	}
	@GetMapping("/get-all-empl")
	public List<Employee> viewAll(){
		return service.getEmployee();
	}
	@PutMapping("/update-employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp=service.updateEmployee(employee);
		if(emp!=null) {
			return ResponseEntity.ok(emp);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		return service.deleteEmployee(id);
		
	}
	@PutMapping("/add-project-emp/{id}")
	public ResponseEntity<String> insertEmployeeProject(@PathVariable Long id, @RequestBody List<Long> projectIds) {
	    return service.insertEmployeeProject(id, projectIds);
	}
	@GetMapping("/get-employee-project/{id}")
	public ResponseEntity<EmployeeProject> getEmployeeProject(@PathVariable Long id) {
		EmployeeProject emp=service.getEmployeeProject(id);
		if(emp!=null) {
			return ResponseEntity.ok(emp);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}


}
