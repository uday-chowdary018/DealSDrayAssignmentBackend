package com.uday.kiran.emp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uday.kiran.emp.entitys.Employee;
import com.uday.kiran.emp.repository.EmployeeRepository;
import com.uday.kiran.emp.services.EmployeeService;
@Service
public class EmployeeServiceimpl implements EmployeeService {
	  
	@Autowired
	    private EmployeeRepository employeeRepository;

	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    public Employee getEmployeeById(Long id) {
	        return employeeRepository.findById(id).orElse(null);
	    }

	    public Employee saveEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public void deleteEmployee(Long id) {
	        employeeRepository.deleteById(id);
	    }

		public boolean isEmailExists(String email) {
			 
			return employeeRepository.existsByEmail(email);
		}

	

		public long getEmployeeCount() {
			return employeeRepository.count();
		}

		

	@Override
	public Page<Employee> getAllEmployeesPaged(Pageable pageable) {
		return employeeRepository.findAll(pageable);
		}
	
	
}
