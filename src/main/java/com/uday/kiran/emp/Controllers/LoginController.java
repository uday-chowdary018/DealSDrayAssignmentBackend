package com.uday.kiran.emp.Controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uday.kiran.emp.entitys.Login;
import com.uday.kiran.emp.entitys.dto.LogintRequest;
import com.uday.kiran.emp.services.impl.LoginServiceimpl;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import jakarta.websocket.Session;
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {
	
	   @Autowired
	    private LoginServiceimpl loginService;

	   @PostMapping("/authenticate")
	    public ResponseEntity<String> authenticate(@RequestBody LogintRequest loginRequest,  HttpSession session) {
	        Login login = loginService.findByUsername(loginRequest.getUserName());
	        if (login != null && login.getPassword().equals(loginRequest.getPassword())) {
	        	 session.setAttribute("username", login.getUserName()); // Set username in session
	        
	        	 return ResponseEntity.ok("Success");
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect");
	    }
	    @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody Login login) {
	        if (!login.getUserName().contains("@")) {
	            return ResponseEntity.badRequest().body("Username must be an email");
	        }

	        if (login.getPassword() == null || login.getPassword().length() < 6) {
	            return ResponseEntity.badRequest().body("Password must be at least 6 characters long");
	        }

	        
	        if (loginService.findByUsername(login.getUserName()) != null) {
	            return ResponseEntity.badRequest().body("Username already exists");
	        }

	       
	        loginService.saveLogin(login);
	        return ResponseEntity.ok("Account created successfully");
	    }
	    

	    @GetMapping("/userinfo")
	    public ResponseEntity<String> getUserInfo(HttpSession session) {
	        String username = (String) session.getAttribute("username");
	        if (username != null) {
	            return ResponseEntity.ok(username);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in");
	        }
	    }


}
