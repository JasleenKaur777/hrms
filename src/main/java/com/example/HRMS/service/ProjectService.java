package com.example.HRMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.HRMS.dao.ProjectRepository;
import com.example.HRMS.model.Employee;
import com.example.HRMS.model.Project;

@Service
public class ProjectService {
	@Autowired
ProjectRepository repo;
	public void addProject(Project project) {
		repo.save(project);
	}
	public List<Project> viewAll(){
		return repo.findAll();
	}
	public ResponseEntity<String> deleteProject(Long id) {
		Optional<Project> opt= repo.findById(id);
		if(opt.isPresent()) {
			Project project=opt.get();
			for (Employee emp : project.getEmployees()) {
				emp.getProjectlist().remove(project);
			}
			repo.delete(project);
			return new ResponseEntity<String>("Delete the project",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Project is not deleted",HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<Project> updateProject(Project project){
		Optional<Project> opt= repo.findById(project.getId());
		if(opt.isPresent()) {
			Project proj=opt.get();
			
			proj.setName(project.getName());
			proj.setDescription(project.getDescription());
			repo.save(proj);
			return ResponseEntity.ok(proj);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
