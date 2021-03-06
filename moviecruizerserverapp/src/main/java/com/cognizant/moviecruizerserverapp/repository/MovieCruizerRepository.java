package com.cognizant.moviecruizerserverapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruizerserverapp.domain.Movie;

@Repository
public interface MovieCruizerRepository extends JpaRepository<Movie, Integer> {
	
	public List<Movie> findByUserId(String userId);
	
	public Movie findByUserIdAndTitle(String userid, String title);

}
