package com.cognizant.moviecruizerserverapp.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.moviecruizerserverapp.domain.Movie;
import com.cognizant.moviecruizerserverapp.exceptions.MovieAlreadyExistsException;
import com.cognizant.moviecruizerserverapp.exceptions.MovieNotFoundException;
import com.cognizant.moviecruizerserverapp.repository.MovieCruizerRepository;

public class MovieServiceImplTest {
	
	@Mock
	private MovieCruizerRepository movieRepo;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	private Movie movie;
	
	private Optional<Movie> options;
	
	@Before
	public void setupMock()
	{
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1, "301", "Gladiator", "warrior movie",
				"www.xyz.com", "content", "2010-10-01", "arul");
		options = Optional.of(movie);
	}
	
	@Test
	public void testMockCreations()
	{
		assertNotNull("MovieRepo creation fails", movieRepo);
	}
	
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException
	{
		when(movieRepo.save(movie)).thenReturn(movie);
		when(movieRepo.findByUserIdAndTitle(movie.getUserId(), movie.getTitle())).thenReturn(null);
		final boolean flag = movieService.saveMovie(movie);
		assertTrue("saveMovie failed", flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findByUserIdAndTitle(movie.getUserId(), movie.getTitle());
	}
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException
	{
		when(movieRepo.findByUserIdAndTitle(movie.getUserId(), movie.getTitle())).thenReturn(movie);
		final boolean flag = movieService.saveMovie(movie);
		assertFalse("saveMovie succeded", flag);
		verify(movieRepo, times(1)).findByUserIdAndTitle(movie.getUserId(), movie.getTitle());
	}
	
	@Test 
	public void testUpdateMovie() throws MovieNotFoundException
	{
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("good movie");
		final Movie movie1 = movieService.updateMovie(movie);
		assertEquals("update failed", "good movie", movie1.getComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testDeleteMovie() throws MovieNotFoundException
	{
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		final boolean flag = movieService.deleteMovieById(1);
		assertTrue("delete movie failed", flag);
		verify(movieRepo, times(1)).delete(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMovieById() throws MovieNotFoundException
	{
		when(movieRepo.findById(1)).thenReturn(options);
		final Movie movie1 = movieService.getMovieById(1);
		assertNotNull("Get Movie failed", movie1);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMyMovies()
	{
		final List<Movie> list = new ArrayList<Movie>(1);
		when(movieRepo.findByUserId("arul")).thenReturn(list);
		final List<Movie> list1 = movieService.getMyMovies("arul");
		assertEquals(list, list1);
		verify(movieRepo, times(1)).findByUserId("arul");
		
	}

}
