package com.cognizant.MovieCruiserAuthenticationService.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.MovieCruiserAuthenticationService.model.User;
import com.cognizant.MovieCruiserAuthenticationService.service.SecurityTokenGenerator;
import com.cognizant.MovieCruiserAuthenticationService.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator tokenGenerator;
	
	private User user;
	
	private Map<String, String> map;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		user = new User("arul", "secret", "Arul", "Shanmugam", new Date());
		map = new HashMap<>();
		map.put("token", "token");
	}
	
	@Test
	public void testRegisterUser() throws Exception
	{
		when(userService.saveUser(user)).thenReturn(true);
		mvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isCreated());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void testLoginUser() throws Exception
	{
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
		when(tokenGenerator.generateToken(user)).thenReturn(map);
		mvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());
		verify(userService, times(1)).findByUserName(user.getUserName());
		verify(tokenGenerator, times(1)).generateToken(Mockito.any(User.class));
	}
	
	private static String jsonToString(final Object obj)
	{
		String jsonStr = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			jsonStr = mapper.writeValueAsString(obj);
		}catch(JsonProcessingException e)
		{
			jsonStr = "json processing error";
		}
		return jsonStr;
	}
}
