package com.payrollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrollapp.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

}
