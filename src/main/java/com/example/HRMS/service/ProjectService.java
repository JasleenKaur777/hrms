package com.example.HRMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.HRMS.DTO.EmployeeDTO;
import com.example.HRMS.DTO.ProjectDTO;
import com.example.HRMS.DTO.ResponseMessage;
import com.example.HRMS.dao.EmployeeRepository;
import com.example.HRMS.dao.ProjectRepository;
import com.example.HRMS.exception.ResourceNotFoundException;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
	@Autowired
ProjectRepository repo;
	
	@Autowired
	EmployeeRepository emp_repo;
	
	@Autowired
	ModelMapper mapper;
	public ProjectDTO addProject(ProjectDTO project) {
		Project proj=new Project();
		proj.setDescription(project.getDescription());
		proj.setName(project.getName());
		repo.save(proj);
		return mapper.map(proj, ProjectDTO.class);
	}
	
	@Transactional
	public ProjectDTO addEmployeeToProject(Long projectId, Long employeeId) {
	    Project project = repo.findById(projectId)
	        .orElseThrow(() -> new ResourceNotFoundException("Project", "Project ID", projectId));

	    Employee employee = emp_repo.findById(employeeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee ID", employeeId));

	    // Initialize list if null
	    if (project.getEmployees() == null) {
	        project.setEmployees(new ArrayList<>());
	    }

	    // Add employee into project
	    if (!project.getEmployees().contains(employee)) {
	        project.getEmployees().add(employee);
	    }

	    // Save project
	    repo.save(project);

	    // Fetch updated project
	    Project updatedProject = repo.findById(projectId)
	        .orElseThrow(() -> new ResourceNotFoundException("Project", "Project ID", projectId));

	    // Map to DTO manually
	    ProjectDTO projectDTO = mapper.map(updatedProject, ProjectDTO.class);
	    List<EmployeeDTO> employeeDTOs = updatedProject.getEmployees()
	        .stream()
	        .map(emp -> mapper.map(emp, EmployeeDTO.class))
	        .collect(Collectors.toList());

	    projectDTO.setEmployees(employeeDTOs);

	    return projectDTO;
	}

	public List<ProjectDTO> viewAll() {
	    List<Project> projects = repo.findAll();  // now normal findAll is enough

	    List<ProjectDTO> dtos = projects.stream().map(project -> {
	        ProjectDTO projectDTO = new ProjectDTO();
	        projectDTO.setId(project.getId());
	        projectDTO.setName(project.getName());
	        projectDTO.setDescription(project.getDescription());

	        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
	        if (project.getEmployees() != null) {
	            employeeDTOs = project.getEmployees().stream().map(emp -> {
	                EmployeeDTO employeeDTO = new EmployeeDTO();
	                employeeDTO.setName(emp.getName());
	                employeeDTO.setEmail(emp.getEmail());
	                employeeDTO.setPosition(emp.getPosition());
	                employeeDTO.setDepartment(null);  // optional: avoid infinite mapping
//	                employeeDTO.setProjects(null);    // optional: avoid infinite mapping
	                return employeeDTO;
	            }).collect(Collectors.toList());
	        }

	        projectDTO.setEmployees(employeeDTOs);
	        return projectDTO;
	    }).collect(Collectors.toList());

	    return dtos;
	}


//	public ResponseEntity<String> deleteProject(Long id) {
//		Optional<Project> opt= repo.findById(id);
//		if(opt.isPresent()) {
//			Project project=opt.get();
//			for (Employee emp : project.getEmployees()) {
//				emp.getProjectlist().remove(project);
//			}
//			repo.delete(project);
//			return new ResponseEntity<String>("Delete the project",HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<String>("Project is not deleted",HttpStatus.NOT_FOUND);
//	}
//	public ResponseEntity<Project> updateProject(Long id,Project project){
//		Project opt= repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Project", "project id", id));	
//			opt.setDescription(project.getDescription());
//			opt.setName(project.getName());
//			repo.save(opt);
//			return new ResponseEntity<Project>(opt,HttpStatus.ACCEPTED);	
//		
//	}
}
