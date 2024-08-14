package com.uday.kiran.emp.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.sql.results.graph.embeddable.internal.EmbeddableAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.uday.kiran.emp.entitys.Employee;
import com.uday.kiran.emp.services.impl.EmployeeServiceimpl;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	



    @Autowired
    private EmployeeServiceimpl employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/count")
    public long getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String designation,
            @RequestParam String gender,
            @RequestParam String courses,
            @RequestParam("image") MultipartFile image) throws IOException {
        
    	 if (name == null || email == null  || phone == null || designation == null || gender == null || courses == null || image == null) {
    	        throw new IllegalArgumentException("All fields must be provided");
    	    }

    	    if (!email.matches("\\S+@\\S+\\.\\S+")) {
    	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email address format");
    	    }

    	    if (!phone.matches("\\d+")) {
    	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number must be numeric");
    	    }

    	    if (!image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/png")) {
    	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only JPEG and PNG images are allowed");
    	    }

    	    
    	    if (employeeService.isEmailExists(email)) {
    	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
    	    }
    	 
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDesignation(designation);
        employee.setGender(gender);
        employee.setCourse(courses); 
        employee.setCreatedate(Date.valueOf(LocalDate.now()));
      
        if (image != null && !image.isEmpty()) {
            employee.setImage(image.getBytes());
        }
		
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String designation,
            @RequestParam String gender,
            @RequestParam String courses,
            @RequestParam("image") MultipartFile image) throws IOException {
        
        Employee employee = new Employee();
       
        
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDesignation(designation);
        employee.setGender(gender);
        employee.setCourse(courses);  
        if (image != null && !image.isEmpty()) {
            employee.setImage(image.getBytes()); 
        }
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    
    @GetMapping("/paged")
    public Page<Employee> getAllEmployeesPaged(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "active") String status) {

        Sort sort = Sort.by(Sort.Order.by(sortBy));
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employees = employeeService.getAllEmployeesPaged(pageable);

        return employees;
    }
    
    
    
    

}
