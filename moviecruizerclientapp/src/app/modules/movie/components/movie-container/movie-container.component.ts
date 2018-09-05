import { Component, OnInit, Input, Output } from '@angular/core';

import { Movie } from '../../movie'
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'movie-container',
  templateUrl: './movie-container.component.html',
  styleUrls: ['./movie-container.component.css']
})
export class MovieContainerComponent implements OnInit {
  @Input()
  movies : Array<Movie>;
  @Input()
  isWatchlist: boolean;
  constructor(private movieService: MovieService, private snackBar: MatSnackBar) { 
  }

  ngOnInit() {
  }

  addToWatchlist(movie)
  {
    this.movieService.addMovieToWatchlist(movie).subscribe((movie) =>
    {
      this.snackBar.open("Movie added to watchlist", "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 1000});
    }
  );
  }

  deleteFromWatchlist(movie: Movie)
  {
    let message = `${movie.title} deleted from watchlist`
    this.movieService.deleteMovieFromWatchlist(movie).subscribe((m) =>
    {
      const index = this.movies.indexOf(movie);
    this.movies.splice(index, 1);
      this.snackBar.open(message, "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 1000});
    }
  );
  }

  updateWatchlist(movie)
  {
    this.movieService.updateMovieWatchlist(movie).subscribe((movie) =>
    {
      this.snackBar.open("Movie " + movie.title + " updated", "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 1000});
    });
  } 

}
