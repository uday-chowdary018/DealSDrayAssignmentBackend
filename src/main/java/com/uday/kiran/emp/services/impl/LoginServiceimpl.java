package com.uday.kiran.emp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.kiran.emp.entitys.Login;
import com.uday.kiran.emp.repository.LoginRepository;
@Service
public class LoginServiceimpl {
	
	 @Autowired
	    private LoginRepository loginRepository;

	    public Login findByUsername(String userName) {
	        return loginRepository.findByuserName(userName);
	    }

	    public Login saveLogin(Login login) {
	        return loginRepository.save(login);
	    }

}
