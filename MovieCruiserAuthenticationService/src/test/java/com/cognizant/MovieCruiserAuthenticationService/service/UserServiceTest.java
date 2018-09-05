package com.cognizant.MovieCruiserAuthenticationService.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserAlreadyExistsException;
import com.cognizant.MovieCruiserAuthenticationService.exceptions.UserNotFoundException;
import com.cognizant.MovieCruiserAuthenticationService.model.User;
import com.cognizant.MovieCruiserAuthenticationService.repository.UserRepository;

public class UserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	private User user;
	
	private Optional<User> options;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		user = new User("arul", "secret", "Arul", "Shanmugam", new Date());
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws Exception
	{
		when(userRepo.save(user)).thenReturn(user);
		final boolean flag = userService.saveUser(user);
		assertTrue("saveUser failed",flag);
		verify(userRepo, times(1)).save(user);
		verify(userRepo, times(1)).findById(user.getUserName());
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws Exception
	{
		when(userRepo.save(user)).thenReturn(user);
		when(userRepo.findById(user.getUserName())).thenReturn(options);
		final boolean flag = userService.saveUser(user);
		assertFalse("saveUser failed",flag);
	}
	
	@Test
	public void testFindByUserNameSuccess() throws Exception
	{
		when(userRepo.findById(user.getUserName())).thenReturn(options);
		User user1 = userService.findByUserName(user.getUserName());
		assertNotNull("find user failed", user1);
		verify(userRepo, times(1)).findById(user.getUserName());
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testFindByUserNameFailure() throws Exception
	{
		when(userRepo.findById(user.getUserName())).thenReturn(Optional.empty());
		User user1 = userService.findByUserName(user.getUserName());
		assertNull("find user failed", user1);
	}
}
