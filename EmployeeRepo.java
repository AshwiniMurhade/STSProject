package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

	Employee findByEmpUsername(String username);

	List<Employee> findByManagerId(int managerId);

	
	

	

	
}
