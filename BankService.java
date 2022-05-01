package com.example.demo.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionType;
import com.example.demo.entity.Transactions;
import com.example.demo.entity.Users;
import com.example.demo.error.InvalidException;
import com.example.demo.error.LowBalanceException;
import com.example.demo.error.NoTransactionException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.repository.TransactionRepo;
import com.example.demo.repository.UserRepo;

@Service
public class BankService implements UserDetailsService {

	@Autowired
	UserRepo repo;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	
	@Autowired
	TransactionRepo trepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Username not found!!");
		return new BankPrincipal(user);
	}

	public Users saveUser(Users user) {
		user.setPassword(passwordencoder.encode(user.getPassword()));
				return repo.save(user);
	}

	public String removeUser(long acno) throws UserNotFoundException {
		if(!repo.existsByAcno(acno)) 
			throw new UserNotFoundException("Username not found!!");	
		else
			repo.deleteByAcno(acno);
		return "Record Deleted!";
			
	}

	public List<Users> getUsers() {
		return repo.findAll();
	}

	public List<Users> getTrans(int n) throws NoTransactionException {
		
		List<Users> tr = repo.findAll();
		List<Users> res = new ArrayList<>();
		if(tr.size()==0)
			throw new NoTransactionException("No Transactions Found");
		else {
			for(int i = 0; i < n && i < tr.size(); i++) {
				if(tr.get(i).getTransactions().size()!=0)
					res.add(tr.get(i));
			}
			return res;
	}
		
		}

	public Users updateUser(long acno, Users user) throws UserNotFoundException {
		Users u;
		if(repo.existsByAcno(acno)) {
			u = repo.findByAcno(acno);
			if(Objects.nonNull(user.getName())   || !"".equalsIgnoreCase(user.getName()))
			    u.setName(user.getName());
			if(Objects.nonNull(user.getAddress()) || !"".equals(user.getAddress()))
				u.setAddress(user.getAddress());
			if(Objects.nonNull(user.getCellno()))
				u.setCellno(user.getCellno());
			if(Objects.nonNull(user.getEmail()) || !"".equals(user.getEmail()))
				u.setEmail(user.getEmail());
			return repo.save(u);
		}
		else
			throw new UserNotFoundException("Username not Found!!");
	}

	public Users getUser(long acno) {
		
		return repo.findByAcno(acno);
	}

	public Users updateUserPass(long acno, Users user) {
		Users u = repo.findByAcno(acno);
		
			if(Objects.nonNull(user.getName())   || !"".equalsIgnoreCase(user.getName()))
			    u.setName(user.getName());
			if(Objects.nonNull(user.getAddress()) || !"".equals(user.getAddress()))
				u.setAddress(user.getAddress());
			if(Objects.nonNull(user.getCellno()))
				u.setCellno(user.getCellno());
			if(Objects.nonNull(user.getEmail()) || !"".equals(user.getEmail()))
				u.setEmail(user.getEmail());
		if(Objects.nonNull(user.getUsername()) || !"".equals(user.getUsername()))
			u.setUsername(user.getUsername());
		if(Objects.nonNull(user.getPassword()) || !"".equals(user.getPassword()))
			u.setPassword(passwordencoder.encode(user.getPassword()));
		
		return repo.save(u);
	}
	
	public List<Transactions> getTransactions(long acno, int n) throws NoTransactionException {
		
		List<Transactions> tr =  trepo.findByUserAcnoOrderByTimeDesc(acno);
		List<Transactions> res = new ArrayList<>();
		if(tr.size()==0) 
		 throw new 	NoTransactionException(" no user transaction found ");
		else {
		for(int i = 0; i < n && i<tr.size(); i++) {
			res.add(tr.get(i));
		}
		return  res;
		}
	}

	public String depwit(long acno, Transactions tr) throws Exception {
		Users user = repo.findByAcno(acno);
		double amount, amt;
		Date td=new Date();
		tr.setTime(td);
		tr.setUser(user);
		user.getTransactions().add(tr);
		if(tr.getTrtype().equals(TransactionType.DEPOSIT)) {
			amount = user.getBalance() + tr.getAmount();
			user.setBalance(amount);
			repo.save(user);
			trepo.save(tr);
		}
		else if(tr.getTrtype().equals(TransactionType.WITHDRAW)) {
			if(user.getBalance()<tr.getAmount()) {
				throw new LowBalanceException("Your account balance is low");
			}
			amount = user.getBalance() - tr.getAmount();
			user.setBalance(amount);
			repo.save(user);
			trepo.save(tr);
		}
		else if(tr.getTrtype().equals(TransactionType.TRANSFER)){
			if(!repo.existsByAcno(tr.getRacno())) {
				throw new UserNotFoundException("Username not found");
			}
			else {
				Users reciever = repo.findByAcno(tr.getRacno());
				if(user.getBalance()<tr.getAmount()) {
					throw new LowBalanceException("Your account balance is low");
				}
				amount = user.getBalance() - tr.getAmount();
				user.setBalance(amount);
				amt = reciever.getBalance() + tr.getAmount();
				reciever.setBalance(amt);
				repo.save(reciever);
				repo.save(user);
				trepo.save(tr);
			}
		}
		else {
			throw new InvalidException("Invalid transaction type");
		}
		return "Transaction Successful";
	}

	public String displayBal(long acno) {
		Users user = repo.findByAcno(acno);
		return "Your account balance is Rs."+user.getBalance();
	}

	public List<Transactions> getTransByType(TransactionType type) {
		// TODO Auto-generated method stub
		return trepo.findByTrtypeOrderByTimeDesc(type);
	}

	

	
}
