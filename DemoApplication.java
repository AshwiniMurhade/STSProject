package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(DemoApplication.class, args); 
		Student s=ctx.getBean("student",Student.class);
		System.out.println("my student object"+s);
		Student s1=ctx.getBean("student",Student.class);
		System.out.println("my student object"+s1);
	}

}
