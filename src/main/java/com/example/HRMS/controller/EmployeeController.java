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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.HRMS.DTO.EmployeeDTO;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;
import com.example.HRMS.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@PostMapping("/api/employees")
	public ResponseEntity<EmployeeDTO> insertEmployee(
	        @Valid @RequestBody EmployeeDTO employee,
	        @RequestParam Long departmentId,
	        @RequestParam List<Long> projectIds) {

	    EmployeeDTO savedEmployee = service.insertEmployeeWithProjects(employee, departmentId, projectIds);
	    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	@GetMapping("/get-all-empl")
	public List<EmployeeDTO> viewAll(){
		return service.getEmployee();
	}
	@GetMapping("/get-employee/{id}")
	 public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = service.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
	 @PutMapping("/{id}")
	    public ResponseEntity<EmployeeDTO> updateEmployee(
	            @PathVariable Long id,
	            @RequestParam Long departmentId,
	            @RequestParam List<Long> projectIds,
	            @RequestBody EmployeeDTO updatedEmployee) {
	        EmployeeDTO result = service.updateEmployee(id, departmentId, projectIds, updatedEmployee);
	        return ResponseEntity.ok(result);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        service.deleteEmployee(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PutMapping("/{id}/assign-projects")
	    public ResponseEntity<EmployeeDTO> assignProjectsToEmployee(
	            @PathVariable Long id, @RequestBody List<Long> projectIds) {
	        EmployeeDTO result = service.assignProjects(id, projectIds);
	        return ResponseEntity.ok(result);
	    }

	    @PutMapping("/{id}/assign-department/{deptId}")
	    public ResponseEntity<EmployeeDTO> assignDepartmentToEmployee(
	            @PathVariable Long id, @PathVariable Long deptId) {
	        EmployeeDTO result = service.assignDepartment(id, deptId);
	        return ResponseEntity.ok(result);
	    }

	    @GetMapping("/by-project/{projectId}")
	    public ResponseEntity<List<EmployeeDTO>> getEmployeesByProject(@PathVariable Long projectId) {
	        return ResponseEntity.ok(service.getEmployeesByProject(projectId));
	    }

	    @GetMapping("/by-department/{deptId}")
	    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable Long deptId) {
	        return ResponseEntity.ok(service.getEmployeesByDepartment(deptId));
	    }
		

}
