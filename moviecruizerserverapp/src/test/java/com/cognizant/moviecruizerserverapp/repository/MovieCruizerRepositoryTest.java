package com.cognizant.moviecruizerserverapp.repository;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import com.cognizant.moviecruizerserverapp.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MovieCruizerRepositoryTest {

	@Autowired
	private MovieCruizerRepository repo;
	
	@Test
	@Rollback
	public void testSaveMovie()
	{
		Movie movie = repo.save(new Movie(1, "201", "Real Steel", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "arul"));
		Movie movie1 = repo.getOne(movie.getId());
		assertThat(movie1.getTitle()).isEqualTo("Real Steel");
	}
	
	@Test
	@Rollback
	public void testUpdateMovie()
	{
		Movie movie = repo.save(new Movie(2, "202", "Real Steel", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "arul"));
		Movie movie1 = repo.getOne(movie.getId());
		assertThat(movie1.getTitle()).isEqualTo("Real Steel");
		movie1.setComments("watch");
		repo.save(movie1);
		movie1 = repo.getOne(movie1.getId());
		assertThat(movie1.getComments()).isEqualTo("watch");
	}
	
	@Test
	@Rollback
	public void testDeleteMovie()
	{
		Movie movie = repo.save(new Movie(3, "203", "Real Steel", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "arul"));
		Movie movie1 = repo.getOne(movie.getId());
		assertThat(movie1.getId()).isEqualTo(movie.getId());
		repo.delete(movie1);
		assertEquals(Optional.empty(), repo.findById(movie1.getId()));
	}
	
	@Test
	@Rollback
	public void testGetMovie()
	{
		Movie movie = repo.save(new Movie(4, "204", "titanic", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "arul"));
		Movie movie1 = repo.getOne(movie.getId());
		assertThat(movie1.getTitle()).isEqualTo("titanic");
	}
	
	@Test
	@Rollback
	public void testGetMyMovies()
	{
		repo.save(new Movie(5, "205", "Real Steel", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "murugan"));
		repo.save(new Movie(6, "206", "Gladiator", "Robo boxing",
				"www.xyz.com", "content", "2010-10-01", "murugan"));
		List<Movie> list = repo.findByUserId("murugan");
		assertThat(list.get(0).getTitle()).isEqualTo("Real Steel");
	}
	
}
