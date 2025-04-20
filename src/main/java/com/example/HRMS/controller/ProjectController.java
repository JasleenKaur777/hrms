package com.example.HRMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HRMS.DTO.ProjectDTO;
import com.example.HRMS.model.Project;
import com.example.HRMS.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
ProjectService service;
	@PostMapping("/addproject")
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO project) {
		ProjectDTO dto= service.addProject(project);
		return new ResponseEntity<ProjectDTO>(dto,HttpStatus.ACCEPTED);
	}
//	@GetMapping("/getproject")
//	public List<Project> viewAll(){
//		return service.viewAll();
//	}
//	@DeleteMapping("/delete-project/{id}")
//	public ResponseEntity<String> deleteProject(@PathVariable Long id){
//		return service.deleteProject(id);
//	}
//	@PutMapping("/update-project/{id}")
//	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
//		return service.updateProject(id,project);
//	}
}
