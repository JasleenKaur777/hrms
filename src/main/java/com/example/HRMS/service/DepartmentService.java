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
	public Department addEmployeeToDept(Long id,List<Long> employeeId) {
	  Department dept=	deprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","Department Id",id));
	  List<Employee> employeeid=employee_repo.findAllById(employeeId);
	  HashSet<Employee> hashemp=new HashSet<Employee>(employeeid);
	  if(hashemp.isEmpty()) {
		  throw new ResourceNotFoundException("Employee", "Employee Ids", 0L); 
	  }
	  for (Employee employee : employeeid) {
		employee.setDepartment(dept);
	}
	  dept.getEmployees().addAll(hashemp);
	  deprepo.save(dept);
	  employee_repo.saveAll(employeeid);
	  return dept;
	}
}
