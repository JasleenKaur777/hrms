package com.example.HRMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HRMS.model.Department;
import com.example.HRMS.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService service;
	
	@PostMapping("/adddept")
	public String addDepartment(@RequestBody Department dept) {
		service.addDept(dept);
		return "department is inserted";
	}
	@GetMapping("/getdept")
	public List<Department> viewAll(){
		return service.viewAll();
	}
	@PutMapping("/add-employee-to-dept/{id}")
	public ResponseEntity<Department> addEmployeeToDepartment(@PathVariable Long id,@RequestBody List<Long> Employee_id){
		Department dept=service.addEmployeeToDept(id, Employee_id);
		return new ResponseEntity<Department>(dept,HttpStatus.ACCEPTED);
	}
}
