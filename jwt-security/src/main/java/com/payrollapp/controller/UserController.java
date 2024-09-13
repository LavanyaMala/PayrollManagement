package com.payrollapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payrollapp.model.User;
import com.payrollapp.repository.UserRepository;

@RestController
 public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello, User!";
    }

    @PostMapping("/auth/signup")
    public void signup(@RequestBody User user) {
    	user.setRoletype("ROLE_ADMIN"); 
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello, Admin!";
    }
}
 
