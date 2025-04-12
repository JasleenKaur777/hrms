package com.example.HRMS.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="employee")
@Component
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is mandatory")
    @Column(nullable = false, unique = true)
	private String name;
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Column(nullable = false)
	private String email;
	 @NotBlank(message = "Position is mandatory")
	private String position;
	
	
	 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "employee_project",
	        joinColumns = @JoinColumn(name = "employee_id"),
	        inverseJoinColumns = @JoinColumn(name = "project_id")
	    )
	 @JsonIgnore
	List<Project> projectlist;
	 @ManyToOne
	 @JoinColumn(name = "department_id")
	 private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Project> getProjectlist() {
		return projectlist;
	}

	public void setProjectlist(List<Project> projectlist) {
		this.projectlist = projectlist;
	}

	public Employee(Long id, String name, String email, String position) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		
	}

	public Employee(String name, String email, String position) {
		super();
		this.name = name;
		this.email = email;
		this.position = position;
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
	

}
