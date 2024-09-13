package com.payrollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payrollapp.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

	
	
	
	
	@Query("SELECT e.manager FROM Employee e JOIN e.user u where u.username=?1")
	Manager findManagerByEmployeeUsername(String loggedInUsername);



}
