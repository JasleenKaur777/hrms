package com.example.HRMS.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HRMS.dao.DepartmentRepository;
import com.example.HRMS.dao.EmployeeRepository;
import com.example.HRMS.exception.ResourceNotFoundException;
import com.example.HRMS.model.Department;
import com.example.HRMS.model.Employee;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository deprepo;
	
	@Autowired
	EmployeeRepository employee_repo;
	
	public void addDept(Department dept) {
		deprepo.save(dept);
	}
	public List<Department> viewAll(){
		return deprepo.findAll();
	}
	public Department addEmployeeToDept(Long id, List<Long> employeeIds) {
	    Department dept = deprepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Department", "Department Id", id));

	    List<Employee> employees = employee_repo.findAllById(employeeIds);
	    if (employees.isEmpty()) {
	        throw new ResourceNotFoundException("Employee", "Employee Ids", 0L);
	    }

	    for (Employee employee : employees) {
	        employee.setDepartment(dept);
	    }

	    dept.getEmployees().addAll(employees); 
	    employee_repo.saveAll(employees);

	    return deprepo.save(dept);
	}
	public Department getDepartmentById(Long id) {
		 Department dept = deprepo.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Department", "Department Id", id));
		return dept;
	}
	public Boolean deleteDepartment(Long id) {
		Department dept = deprepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Department", "Department Id", id));
		
		if(dept!=null) {
			 deprepo.delete(dept); 
			return true;
			
		}
		else {
			return false;
		}
		
	}
	

}
