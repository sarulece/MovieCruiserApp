import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Movie } from '../../movie'
import { MovieService } from '../../movie.service';
@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {
  movieType : string;
  movies : Array<Movie>;
  constructor(private movieService: MovieService, private activatedRoute : ActivatedRoute) { 
    this.movies = [];
    this.activatedRoute.data.subscribe((data) => {
      this.movieType = data.movieType;
      });
  }

  ngOnInit() {
    this.movieService.getMovies(this.movieType).subscribe((movies)=>{
      this.movies.push(...movies);
      console.log("TMDB: " + this.movies);
    });
  }

}
