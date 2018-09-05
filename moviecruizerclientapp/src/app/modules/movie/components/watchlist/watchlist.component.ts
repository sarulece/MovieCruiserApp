import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies : Array<Movie>;
  isWatchlist = true;
  
  constructor(private movieService: MovieService, private snackBar: MatSnackBar) { 
    this.movies = [];
  }

  ngOnInit() {
    this.getWatchlist();
  }

  getWatchlist()
  {
    console.log("Get movie watchlist...")
    this.movies = [];
    this.movieService.getMovieWatchlist().subscribe((movies) =>
    {
      if(movies.length === 0)
      {
        this.snackBar.open("Watchlist is empty", '', {duration: 1000})
      }
      this.movies.push(...movies);
    });
  }

}
