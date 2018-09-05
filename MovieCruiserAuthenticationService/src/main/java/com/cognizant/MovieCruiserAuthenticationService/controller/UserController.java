package com.cognizant.MovieCruiserAuthenticationService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cognizant.MovieCruiserAuthenticationService.model.User;
import com.cognizant.MovieCruiserAuthenticationService.service.SecurityTokenGenerator;
import com.cognizant.MovieCruiserAuthenticationService.service.UserService;

@RestController
@EnableWebMvc
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) 
	{
		ResponseEntity<?> entity = null;
		try
		{
			userService.saveUser(user);
			entity = new ResponseEntity<String>("User registration successfull", HttpStatus.CREATED);
		}catch(Exception e)
		{
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return entity;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginUser)  
	{
		ResponseEntity<?> entity = null;
		try
		{
			if(loginUser.getUserName() == null || loginUser.getPassword() == null)
			{
				throw new Exception("User name or password can not be empty");
			}
			
			User user = userService.findByUserName(loginUser.getUserName());
			if(!user.getPassword().equals(loginUser.getPassword()))
			{
				throw new Exception("Invalid credential");
			}
			
			Map<String, String> map = tokenGenerator.generateToken(user);
			entity = new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);		
			
		}catch(Exception e)
		{
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return entity;
	}

}
