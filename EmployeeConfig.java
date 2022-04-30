package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class EmployeeConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsService userdetailsservice;
	
	@Bean
	PasswordEncoder  encoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userdetailsservice);
		dao.setPasswordEncoder(encoder());
		return dao;
	}
  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	http
	.csrf().disable()
	.authorizeRequests()
	.antMatchers("/")
	.permitAll()
	.antMatchers("/employee/")
	.hasAuthority("2")
	.antMatchers("/director/")
	.hasAuthority("0")
	.antMatchers("/manager/")
	.hasAuthority("1")
	.anyRequest()
	.authenticated()
	.and()
	.httpBasic();
	
	
	}
	
}
