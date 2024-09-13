package com.payrollapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrollapp.exception.InputInvalidException;
import com.payrollapp.model.Leave;
import com.payrollapp.service.EmployeeService;
import com.payrollapp.service.LeaveService;



@RestController
@RequestMapping("/employee")
public class LeaveController {
	
	@Autowired EmployeeService employeeService;
	@PostMapping("/leave/add")
	public ResponseEntity<?> addLeave(@RequestBody Leave leave, Principal principal) {
	    String loggedInUsername = principal.getName();
	    
	    try {
	        Leave savedLeave = employeeService.addLeave(leave,loggedInUsername);
	        return ResponseEntity.ok(savedLeave);
	    } catch (InputInvalidException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	
}

 
