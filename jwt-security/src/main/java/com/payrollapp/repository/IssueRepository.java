package com.payrollapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrollapp.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
