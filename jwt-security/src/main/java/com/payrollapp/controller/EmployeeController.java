package com.payrollapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrollapp.exception.InputInvalidException;
import com.payrollapp.model.Employee;
import com.payrollapp.service.EmployeeService;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee, @PathVariable int managerId) {
	{
		
		try {
			Employee employees = employeeService.addEmployee(employee,managerId);
		return ResponseEntity.ok(employees);
    } catch (InputInvalidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	
	
	

	}
}
