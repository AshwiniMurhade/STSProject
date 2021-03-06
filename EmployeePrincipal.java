package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Employee;


public class EmployeePrincipal implements UserDetails{

	Employee employee;
	
	public EmployeePrincipal(Employee employee) {
		super();
		this.employee = employee;
	}
 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String un=employee.getEmpDesigination().toString();
		return Collections.singleton(new SimpleGrantedAuthority(un));
	}

	@Override
	public String getPassword() {
	
		return employee.getEmpPassword();
	}

	@Override
	public String getUsername() {
		
		return employee.getEmpUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
