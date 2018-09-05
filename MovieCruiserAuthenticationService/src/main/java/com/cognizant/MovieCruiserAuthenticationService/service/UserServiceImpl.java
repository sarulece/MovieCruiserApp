package com.cognizant.MovieCruiserAuthenticationService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserNotFoundException;
import com.cognizant.MovieCruiserAuthenticationService.model.User;
import com.cognizant.MovieCruiserAuthenticationService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> op = userRepo.findById(user.getUserName());
		if(op.isPresent())
		{
			throw new UserAlreadyExistsException("User name already exists");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserName(String userName) throws UserNotFoundException {
		Optional<User> op = userRepo.findById(userName);
		if(!op.isPresent())
		{
			throw new UserNotFoundException("User name or Password invalid");
		}
		return op.get();
	}

}
