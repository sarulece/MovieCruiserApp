package com.cognizant.moviecruizerserverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruizerserverapp.domain.Movie;
import com.cognizant.moviecruizerserverapp.exceptions.MovieAlreadyExistsException;
import com.cognizant.moviecruizerserverapp.exceptions.MovieNotFoundException;
import com.cognizant.moviecruizerserverapp.repository.MovieCruizerRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieCruizerRepository movieRepository;

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> obj = movieRepository.findById(id);
		if(!obj.isPresent()){
			throw new MovieNotFoundException("Movie not found!");
		}
		return obj.get();
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepository.findByUserId(userId);
	}
	
	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Movie obj = movieRepository.findByUserIdAndTitle(movie.getUserId(), movie.getTitle());
		if(obj != null){
			throw new MovieAlreadyExistsException("Movie already exists. Could not save the movie.");
		}
		movieRepository.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie updateMovie) throws MovieNotFoundException {
		Optional<Movie> obj = movieRepository.findById(updateMovie.getId());
		if(!obj.isPresent()){
			throw new MovieNotFoundException("Movie not found!");
		}
		Movie movie = obj.get();
		movie.setComments(updateMovie.getComments());
		movieRepository.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> obj = movieRepository.findById(id);
		if(!obj.isPresent()){
			throw new MovieNotFoundException("Movie not found!");
		}
		movieRepository.delete(obj.get());
		return true;
	}

}
