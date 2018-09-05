package com.cognizant.MovieCruiserAuthenticationService.repository;


import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.MovieCruiserAuthenticationService.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepo;
	
	private User user;
	
	@Before
	public void setup()
	{
		user = new User("arul", "secret", "Arul", "Shanmugam", new Date());
	}
	
	@Test
	@Rollback
	public void testSaveUser() 
	{
		userRepo.save(user);
		Optional<User> op = userRepo.findById(user.getUserName());
		assertNotNull("User save failed",op.get());
	}

}
