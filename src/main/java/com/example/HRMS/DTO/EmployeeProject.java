package com.example.HRMS.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;

public class EmployeeProject {
private Long id;
private String name;
private String email;
private String position;
private List<Long> projectId;
public EmployeeProject(Employee employee) {
	this.id=employee.getId();
	this.name=employee.getName();
	this.email=employee.getEmail();
	this.position=employee.getPosition();
	this.projectId=employee.getProjectlist().stream().map(Project::getId).collect(Collectors.toList());
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
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
public List<Long> getProjectId() {
	return projectId;
}
public void setProjectId(List<Long> projectId) {
	this.projectId = projectId;
}


}
