package com.example.HRMS.DTO;

import java.util.List;


public class ProjectDTO {
	private Long id;
	private String name;
	private String description;
	private List<EmployeeDTO> employees;
	public ProjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public ProjectDTO(String name, String description, List<EmployeeDTO> employees) {
		super();
		this.name = name;
		this.description = description;
		this.employees = employees;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<EmployeeDTO> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
