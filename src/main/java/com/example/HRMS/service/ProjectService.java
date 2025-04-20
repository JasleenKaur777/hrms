package com.example.HRMS.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.HRMS.DTO.EmployeeProject;
import com.example.HRMS.DTO.ProjectDTO;
import com.example.HRMS.dao.ProjectRepository;
import com.example.HRMS.exception.ResourceNotFoundException;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;

@Service
public class ProjectService {
	@Autowired
ProjectRepository repo;
	
	@Autowired
	ModelMapper mapper;
	public ProjectDTO addProject(ProjectDTO project) {
		Project proj=new Project();
		proj.setDescription(project.getDescription());
		proj.setName(project.getName());
		repo.save(proj);
		return mapper.map(proj, ProjectDTO.class);
	}
//	public List<EmployeeProject> viewAll(){
//		List<Project>
//		return repo.findAll();
//	}
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
