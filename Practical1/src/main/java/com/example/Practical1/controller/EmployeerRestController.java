package com.example.Practical1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.Practical1.entity.Employee;
import com.example.Practical1.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/employees")
public class EmployeerRestController {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeerRestController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
//	Post :- create a new employee
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
//	Get all :- all employee details show
	@GetMapping
	public List<Employee> getAllEmployee(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
//	Get one employee
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable int id){
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
	}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
//	put :- update one employee record 
		@PutMapping("/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable int id , @RequestBody Employee updatedEmployee){
			Optional<Employee> existingEmployee = employeeRepository.findById(id);
			
			if(existingEmployee.isPresent()) {
				Employee extEmployee = existingEmployee.get();
				
				extEmployee.setName(updatedEmployee.getName());
				extEmployee.setDepartment(updatedEmployee.getDepartment());
				extEmployee.setSalary(updatedEmployee.getSalary());
				
				Employee saved = employeeRepository.save(extEmployee);
				
				return ResponseEntity.ok(saved);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
	
//	Delete :- Delete one employee record 
		
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteEmployee(@PathVariable int id){
			if(employeeRepository.existsById(id)){
				employeeRepository.deleteById(id);
				
				return ResponseEntity.ok("Employee with id " + id + " deleted successfully.");
			} else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with id " + id + " not found.");
	        }
		}
	
	

}
