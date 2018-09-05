package com.cognizant.moviecruizerserverapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.moviecruizerserverapp.domain.Movie;
import com.cognizant.moviecruizerserverapp.exceptions.MovieAlreadyExistsException;
import com.cognizant.moviecruizerserverapp.exceptions.MovieNotFoundException;

public interface MovieService {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	
	Movie getMovieById(int id) throws MovieNotFoundException;
	
	List<Movie> getMyMovies(String userId);

}
