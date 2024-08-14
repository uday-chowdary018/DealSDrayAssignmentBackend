package com.uday.kiran.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.kiran.emp.entitys.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmail(String email);

}
