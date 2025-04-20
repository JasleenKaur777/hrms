package com.example.HRMS.DTO;

import java.util.List;

public class EmployeeDTO {
	private String name;
	private String email;
	private String position;
	private DepartmentDTO department;
	private List<ProjectDTO> projects;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDTO(String name, String email, String position, DepartmentDTO department,
			List<ProjectDTO> projects) {
		super();
		this.name = name;
		this.email = email;
		this.position = position;
		this.department = department;
		this.projects = projects;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public DepartmentDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}
	public List<ProjectDTO> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}
	
}
