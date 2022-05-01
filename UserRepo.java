package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transactions;
import com.example.demo.entity.Users;
@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

	Users findByUsername(String username);

	Users findByAcno(long acno);
	
    boolean existsByAcno(long acno);

	

	void deleteByAcno(long acno);

	

}
