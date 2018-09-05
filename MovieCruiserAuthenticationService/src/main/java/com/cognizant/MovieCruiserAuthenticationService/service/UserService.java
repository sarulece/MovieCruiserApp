package com.cognizant.MovieCruiserAuthenticationService.service;

import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserNotFoundException;
import com.cognizant.MovieCruiserAuthenticationService.model.User;

public interface UserService {
	
	public boolean saveUser(User user) throws UserAlreadyExistsException;
	
	public User findByUserName(String userName) throws UserNotFoundException;

}
