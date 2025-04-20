package com.example.HRMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HRMS.DTO.DepartmentDTO;
import com.example.HRMS.DTO.ResponseMessage;
import com.example.HRMS.model.Department;
import com.example.HRMS.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService service;
	
	@PostMapping("/adddept")
	public ResponseEntity<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO dept) {
		DepartmentDTO dto= service.addDept(dept);
		return new ResponseEntity<DepartmentDTO>(dto,HttpStatus.ACCEPTED);
	}
//	@GetMapping("/getdept")
//	public List<Department> viewAll(){
//		return service.viewAll();
//	}
//	//change the department of the employee id mention the department and employeeid mention where to add this department
//	@PutMapping("/add-employee-to-dept/{id}")
//	public ResponseEntity<Department> addEmployeeToDepartment(
//	        @PathVariable Long id,
//	        @RequestBody List<Long> employeeIds) {
//	    Department dept = service.addEmployeeToDept(id, employeeIds);
//	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(dept);
//	}
//	 @GetMapping("/{id}")
//	    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
//	        Department dept = service.getDepartmentById(id);
//	        return ResponseEntity.ok(dept);
//	    }
//	 @DeleteMapping("/{id}")
//	    public ResponseEntity<ResponseMessage> deleteDepartment(@PathVariable Long id) {
//	        Boolean isDeleted=service.deleteDepartment(id);
//	        if(isDeleted) {
//	        	return new ResponseEntity<>(new ResponseMessage(id+ "department id  is deleted ", true),HttpStatus.ACCEPTED);
//	        }
//	        else {
//	        	return new ResponseEntity<>(new ResponseMessage(id+ "department id  is not deleted ", true),HttpStatus.NOT_FOUND);
//	        }
//	        
//	    }

}
