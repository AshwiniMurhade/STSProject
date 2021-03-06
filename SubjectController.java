package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Subject;
import com.example.demo.service.SubjectService;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService subjectservice;
	
   //definition of methods
	
	@RequestMapping("/subjects") //http://localhost:8889/subjects
	public List<Subject> getAllSubjects(){
		
		return subjectservice.getAllSubjects();
	}
 @RequestMapping(method=RequestMethod.POST,value="/subjects")
	public void addSubject(@RequestBody Subject subject) {
		subjectservice.addSubject(subject);
	}
	@RequestMapping(method=RequestMethod.PUT,value="/subjects/{id}")
	public void updateSubject(@PathVariable Integer id,@RequestBody Subject subject) {
		subjectservice.updateSubject(id,subject);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/subjects/{id}")
	public void deleteSubject(@PathVariable Integer id) {
		subjectservice.deleteSubject(id);
	}
}