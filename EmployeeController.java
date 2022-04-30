package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.error.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
    
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/director/all")
	public List<Employee> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/director/getone/{empId}")
	public Employee getSingleEmpByDirector(@PathVariable int empId) throws EmployeeNotFoundException {
		return service.getSingleEmpByDirector(empId);
	}
	
	@GetMapping("/director/{managerid}")
	public List<Employee> getByManagerId(@PathVariable int managerid) {
		return service.getByManagerId(managerid);
	}
	
	@GetMapping("/director/departments")
	public List<Department> getDepartments(){
		return service.getDepartments();
	}
	
	@GetMapping("/director/department/{deptId}")
	public Department getDepartment(@PathVariable int deptId) throws DepartmentNotFoundException {
		return service.getDepartment(deptId);
	}
	
	@PostMapping("/director/addDept")
	public Department addDepartment(@RequestBody Department dept) {
		return service.addDepartment(dept);
	}
	
	@PutMapping("/director/department/{deptId}")
	public Department updateDepartment(@RequestBody Department dept,@PathVariable int deptId) throws DepartmentNotFoundException {
	  return service.updateDepartment(deptId,dept);
	}
	
	@DeleteMapping("/director/department/{deptId}")
	public String deleteDepartment(@PathVariable int deptId) throws DepartmentNotFoundException {
		return service.deleteDepartment(deptId);
	}
	
	@PostMapping("/director/addEmp/{deptId}")
	public Employee addEmployee(@PathVariable int deptId,@RequestBody Employee emp) throws DepartmentNotFoundException, EmployeeNotFoundException {
		return service.addEmployee(deptId,emp);
	}
	
	@PutMapping("/director/update/{empId}")
	public Employee updateUser(@RequestBody Employee emp,@PathVariable int empId) throws EmployeeNotFoundException {
		return service.updateUser(emp,empId);
	}
   
	@DeleteMapping("/director/delete/{empId}")
	public String deleteUser(@PathVariable  int empId) throws EmployeeNotFoundException {
		 return service.deleteUser(empId);
	}
	
	@PutMapping("/director/updateself/{empId}")
	public Employee updateSelfByDirector(@RequestBody Employee emp,@PathVariable int empId ) throws EmployeeNotFoundException {
		return service.updateSelfByDirector(emp,empId);
	}
	
	@GetMapping("/manager/getemp/{managerId}")
	  public List<Employee> getEmployees(@PathVariable int managerId) throws EmployeeNotFoundException {
		return service.getEmployees(managerId);
	}
	
	@PostMapping("/manager/add/{deptId}")
	public Employee addEmployeeByManager(@PathVariable int deptId,@RequestBody Employee emp) throws DepartmentNotFoundException, EmployeeNotFoundException {
		return service.addEmployeeByManager(deptId,emp);
	}
	@PutMapping("/manager/update/{empId}")
	public Employee updateEmployeeByManager(@PathVariable int empId,@RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateEmployeeByManager(empId,emp);
	}
	
	@DeleteMapping("/manager/delete/{empId}")
	public String deleteEmployeeByManager(@PathVariable int empId) throws EmployeeNotFoundException {
		return service.deleteEmployeeByManager(empId);
	}
	
	@PutMapping("/manager/updateself/{empId}")
	public Employee updateSelfByManager(@RequestBody Employee emp,@PathVariable int empId) throws EmployeeNotFoundException {
		return service.updateSelfByManager(emp,empId);
	}
	
	@PutMapping("/employee/update/{empId}")
	public Employee updateSelfByEmployee(@PathVariable int empId,@RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateSelfByEmployee(empId,emp);
	}
	
}