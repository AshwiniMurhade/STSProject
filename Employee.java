package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq" ,initialValue=1000)
	private int  empId;
	private String empName;
	@Length(min=3, message= "Address cannot  be less than characters")
	private String empAddress; 
	@Column(unique = true)
	@Length(min=10, max=13,message="cell number cannot be less than 10 charaters")
	private String empCellNo;
	@Column(unique = true)
	@Email
	private String  empEmail;
	private double empSalary;
	@Column(unique=true)
	private String empUsername;
    private String empPassword;
	private int   empAge;
	@NotNull
	private EmpRole empDesigination;
	private float empExperience;
	private int managerId;
	@JoinColumn(name="deptNo")
	@ManyToOne
	@JsonIgnore
	private Department empdepartments;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpCellNo() {
		return empCellNo;
	}
	public void setEmpCellNo(String empCellNo) {
		this.empCellNo = empCellNo;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public Double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}
	public String getEmpUsername() {
		return empUsername;
	}
	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	
	public EmpRole getEmpDesigination() {
		return empDesigination;
	}
	public void setEmpDesigination(EmpRole empDesigination) {
		this.empDesigination = empDesigination;
	}
	public float getEmpExperience() {
		return empExperience;
	}
	public void setEmpExperience(float empExperience) {
		this.empExperience = empExperience;
	}
	
	public Department getEmpdepartments() {
		return empdepartments;
	}
	public void setEmpdepartments(Department empdepartments) {
		this.empdepartments = empdepartments;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAddress=" + empAddress + ", empCellNo="
				+ empCellNo + ", empEmail=" + empEmail + ", empSalary=" + empSalary + ", empUsername=" + empUsername
				+ ", empPassword=" + empPassword + ", empAge=" + empAge + ", empDesigination=" + empDesigination
				+ ", empExperience=" + empExperience + ", managerId=" + managerId + ", empdepartments=" + empdepartments
				+ "]";
	}
	
	
	
}
