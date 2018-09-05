import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent implements OnInit {
  movies: Array<Movie>;
  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  onEnter(searchKey)
  {
    console.log("SearchKey: " + searchKey);
    this.movieService.searchMovies(searchKey).subscribe( movies =>
    {
      this.movies = movies;
    })
  }

}
