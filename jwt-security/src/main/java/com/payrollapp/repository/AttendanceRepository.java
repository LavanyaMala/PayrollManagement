package com.payrollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrollapp.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}
