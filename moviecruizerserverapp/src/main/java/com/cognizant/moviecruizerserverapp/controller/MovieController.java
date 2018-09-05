package com.cognizant.moviecruizerserverapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviecruizerserverapp.domain.Movie;
import com.cognizant.moviecruizerserverapp.exceptions.MovieAlreadyExistsException;
import com.cognizant.moviecruizerserverapp.exceptions.MovieNotFoundException;
import com.cognizant.moviecruizerserverapp.service.MovieService;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path="/api/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie, HttpServletRequest request,
			HttpServletResponse response)
	{
		ResponseEntity<?> entity = null;
		
		String userId = getUserId(request);
		try
		{
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			entity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		}catch(MovieAlreadyExistsException e)
		{
			entity = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		
		return entity;
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody Movie movie)
	{
		ResponseEntity<?> entity = null;
		try
		{
			Movie updatedMovie = movieService.updateMovie(movie);
			entity = new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
		}catch(MovieNotFoundException e)
		{
			entity = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		
		return entity;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable Integer id)
	{
		ResponseEntity<?> entity = null;
		try
		{
			Movie movie = movieService.getMovieById(id);
			entity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}catch(MovieNotFoundException e)
		{
			entity = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		
		return entity;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMovies(final HttpServletRequest request,
			 final HttpServletResponse response)
	{
		ResponseEntity<?> entity = null;
		String userId = getUserId(request);
		try
		{
			List<Movie> movieList = movieService.getMyMovies(userId);
			entity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		}catch(Exception e)
		{
			entity = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		
		return entity;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Integer id)
	{
		ResponseEntity<?> entity = null;
		try
		{
			boolean status = movieService.deleteMovieById(id);
			entity = new ResponseEntity<String>("Movie deleted successfully", HttpStatus.OK);
		}catch(MovieNotFoundException e)
		{
			entity = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		
		return entity;
	}
	
	private String getUserId(HttpServletRequest request)
	{
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return userId;
	}
	
	

}
