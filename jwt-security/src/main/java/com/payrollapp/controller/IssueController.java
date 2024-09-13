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
import com.payrollapp.model.Attendance;
import com.payrollapp.model.Issue;
import com.payrollapp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class IssueController {
	

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/issue/add")
    public ResponseEntity<?> addIssue(@RequestBody Issue issue, Principal principal) {
        String loggedInUsername = principal.getName();

        try {
            Issue issues = employeeService.addIssue(issue, loggedInUsername);
            return ResponseEntity.ok(issues);
        } catch (InputInvalidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
