package com.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype" ) //to make prototype to get different object
public class Student {
 public Student () {
	 System.out.println("Student object is created");
 }
}
