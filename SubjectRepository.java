package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.bean.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

}
