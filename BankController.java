   package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TransactionType;
import com.example.demo.entity.Transactions;
import com.example.demo.entity.Users;
import com.example.demo.error.NoTransactionException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.service.BankService;

@RestController
public class BankController {


	@Autowired
	BankService service;
	
	@PostMapping("/admin/save/")
	public Users saveUser(@RequestBody Users user) {
		return service.saveUser(user);
	}
	
	@DeleteMapping("/admin/delete/{acno}")
	@Transactional
	public String removeUser(@PathVariable long acno) throws UserNotFoundException {
		return service.removeUser(acno);
	}
	
	@PutMapping("/admin/{acno}")
	public Users updateUser(@PathVariable long acno,@RequestBody Users user) throws UserNotFoundException {
		return service.updateUser(acno, user);
	}
	
	@GetMapping("/admin/")
	public List<Users> getUsers(){
		return service.getUsers();
	}
	
	@GetMapping("/admin/getuser/{acno}")
	public Users getUser(@PathVariable long acno) {
		return service.getUser(acno);
	}
	
	@GetMapping("/admin/gettrans/{n}")
	public List<Users> getTrans(@PathVariable int n) throws NoTransactionException{
		return service.getTrans(n);
	}
	
	@PutMapping("/users/update/{acno}")
	public Users updateUserPass(@PathVariable long acno, @RequestBody Users user) {
		return service.updateUserPass(acno, user);
	}
	
	@GetMapping("/users/{acno}/{n}")
	public List<Transactions> getTransactions(@PathVariable("acno") long acno, @PathVariable("n") int n) throws NoTransactionException {
		return service.getTransactions(acno, n);
	}
	
	
	@PutMapping("/users/{acno}")
	public String depwit(@PathVariable long acno, @RequestBody Transactions tr) throws Exception {
		return service.depwit(acno, tr);
	}
	
	@GetMapping("/users/balance/{acno}")
	public String displayBal(@PathVariable long acno) {
		return service.displayBal(acno);
	}
	
	@GetMapping("/admin/getTrans/trtype/{type}")
	public List<Transactions> getTransByType(@PathVariable TransactionType type) throws NoTransactionException{
		return service.getTransByType(type);
	}
	
	
	
}