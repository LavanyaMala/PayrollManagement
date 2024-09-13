package com.payrollapp.service;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payrollapp.enums.LeaveStatus;
import com.payrollapp.exception.InputInvalidException;
import com.payrollapp.model.Attendance;
import com.payrollapp.model.Employee;
import com.payrollapp.model.Issue;
import com.payrollapp.model.Leave;
import com.payrollapp.model.Manager;
import com.payrollapp.model.User;
import com.payrollapp.repository.AttendanceRepository;
import com.payrollapp.repository.EmployeeRepository;
import com.payrollapp.repository.IssueRepository;
import com.payrollapp.repository.LeaveRepository;
import com.payrollapp.repository.ManagerRepository;
import com.payrollapp.repository.UserRepository;



@Service
public class EmployeeService {
	

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	


	
	public Leave addLeave(Leave leave, String loggedInUsername) throws InputInvalidException {
	    Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	    
	    leave.setSubmittedDate(LocalDate.now());
	    leave.setLeaveStatus(LeaveStatus.Pending);
	    leave.setManager(manager);
	    leave.setEmployee(employee); // Associate the leave with the employee
	    
	    return leaveRepository.save(leave);
	}

	public Attendance addAttendance(Attendance attendance,String loggedInUsername) throws InputInvalidException {
	    
		Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	    attendance.setAttendanceDate(LocalDate.now());
	    attendance.setManager(manager);
	    attendance.setEmployee(employee);
	    return attendanceRepository.save(attendance);
	}

	
	public Issue addIssue(Issue issue, String loggedInUsername) throws InputInvalidException {
		
		Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	   
	    
	    issue.setAppliedDate(LocalDate.now());
	    issue.setEmployee(employee);

	 

	    return issueRepository.save(issue);
	}


	public Employee addEmployee(Employee employee, int managerId) throws InputInvalidException {
		
		User user = employee.getUser();
		user.setRoletype("ROLE_EMPLOYEE");
		//encode the password 
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user); //fully defined user with id 

		
		employee.setUser(user);
		Optional<Manager> optional = managerRepository.findById(managerId);
				if (optional.isEmpty()) {
			        throw new InputInvalidException("Invalid employee ID");
			    }

			    Manager manager = optional.get();
			    
	       
	        employee.setManager(manager);

	        
	        return employeeRepository.save(employee);

		
	}
	
	
   

	

	

	public Manager addManager(Manager manager) {

        return managerRepository.save(manager);
	}

}
