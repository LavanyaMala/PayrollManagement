package com.payrollapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payrollapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = ?1")
	User getUserByUsername(String username);

} 