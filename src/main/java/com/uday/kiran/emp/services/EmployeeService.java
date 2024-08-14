package com.uday.kiran.emp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uday.kiran.emp.entitys.Employee;

public interface EmployeeService {
	
	  Page<Employee> getAllEmployeesPaged(Pageable pageable);
	    long getEmployeeCount();
	    Employee getEmployeeById(Long id);
	    Employee saveEmployee(Employee employee);
	    void deleteEmployee(Long id);
	    boolean isEmailExists(String email);
	

}
