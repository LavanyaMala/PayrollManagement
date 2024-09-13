package com.payrollapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payrollapp.model.Manager;
import com.payrollapp.service.EmployeeService;

@RestController
	@RequestMapping("/managers")
	public class ManagerController {

	    @Autowired
	    private EmployeeService employeeService;

	    @PostMapping("/add")
	    public Manager createManager(@RequestBody Manager manager) {
	        return employeeService.addManager(manager);
	    }

}
