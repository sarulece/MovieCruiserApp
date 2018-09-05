package com.cognizant.moviecruizerserverapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.moviecruizerserverapp.domain.Movie;
import com.cognizant.moviecruizerserverapp.exceptions.MovieAlreadyExistsException;
import com.cognizant.moviecruizerserverapp.service.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	
	@MockBean
	private MovieServiceImpl movieService;
	
	private Movie movie;
	
	@Autowired
	private MockMvc mvc;
	
	private List<Movie> movies;
	
	private String token;
	
	@Before
	public void setup()
	{
		movies = new ArrayList<>();
		movie = new Movie(1, "301", "Gladiator", "warrior movie",
				"www.xyz.com", "content", "2010-10-01", "arul");
		movies.add(movie);
		movie = new Movie(2, "302", "Robo", "superstar movie. super movie",
				"www.abc.com", "overview", "2008-10-01", "arul");
		movies.add(movie);		
		token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcnVsIiwiaWF0IjoxNTMyNTk4ODY2fQ.r7IKam7ABp5yjSgTby41OHCUiI4HB3hbhwi0ss5yQnI";
	}
	
	@Test
	public void testSaveNewMovie() throws Exception
	{
		when(movieService.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movies").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
		
	}
	
	@Test
	public void testUpdateMovie() throws Exception
	{
		movie.setComments("new comments");
		when(movieService.updateMovie(movie)).thenReturn(movie);
		mvc.perform(put("/api/movies/{id}", 2).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
		
	} 
	
	@Test
	public void testDeleteMovie() throws Exception
	{
		when(movieService.deleteMovieById(movie.getId())).thenReturn(true);
		mvc.perform(delete("/api/movies/{id}", 2).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById(2);
		verifyNoMoreInteractions(movieService);
		
	}
	
	@Test
	public void testGetMovieById() throws Exception
	{
		when(movieService.getMovieById(movie.getId())).thenReturn(movie);
		mvc.perform(get("/api/movies/{id}", 2).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).getMovieById(2);
		verifyNoMoreInteractions(movieService);
		
	}
	
	@Test
	public void testGetMyMovies() throws Exception
	{
		when(movieService.getMyMovies(movie.getUserId())).thenReturn(movies);
		mvc.perform(get("/api/movies").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieService, times(1)).getMyMovies(movie.getUserId());
		verifyNoMoreInteractions(movieService);
		
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
