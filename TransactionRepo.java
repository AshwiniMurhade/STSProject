package com.example.demo.repository;

import java.util.List;

//import javax.transaction.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionType;
import com.example.demo.entity.Transactions;

@Repository
public interface TransactionRepo extends JpaRepository <Transactions, Integer>{

	

	

	List<Transactions> findByTrtypeOrderByTimeDesc(TransactionType type);

    List<Transactions> findByUserAcnoOrderByTimeDesc(long acno);

	
	
	
}
