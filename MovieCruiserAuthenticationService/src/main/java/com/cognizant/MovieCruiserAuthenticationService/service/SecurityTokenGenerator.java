package com.cognizant.MovieCruiserAuthenticationService.service;

import java.util.Map;

import com.cognizant.MovieCruiserAuthenticationService.model.User;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
