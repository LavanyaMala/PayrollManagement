package com.payrollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payrollapp.model.Employee;
import com.payrollapp.model.Manager;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e JOIN e.user u where u.username=?1")
	Employee getEmployee(String loggedInUsername);

	 
	
	

 

}
