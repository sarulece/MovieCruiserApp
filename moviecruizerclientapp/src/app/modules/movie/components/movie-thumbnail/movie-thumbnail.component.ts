import { Component, OnInit, Input, Output } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material'
import { EventEmitter } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './movie-thumbnail.component.html',
  styleUrls: ['./movie-thumbnail.component.css']
})
export class MovieThumbnailComponent implements OnInit {

  @Input()
  movie : Movie;
  @Input()
  isWatchlist: boolean;
  @Output()
  addMovie : EventEmitter<Movie>;
  @Output()
  deleteMovie : EventEmitter<Movie>;
  @Output()
  updateMovie : EventEmitter<Movie>;

  constructor(private movieDialog: MatDialog) { 
    this.addMovie = new EventEmitter();
    this.deleteMovie = new EventEmitter();
    this.updateMovie = new EventEmitter();
  }

  ngOnInit() {
   
  }

  addToWatchlist()
  {
    this.addMovie.emit(this.movie);
    console.log("add to watchlist: " + this.movie);
  }

  deleteFromWatchlist()
  {
    this.deleteMovie.emit(this.movie);
  } 

   updateWatchlist(actionType)
  {
    console.log("Updating: " + this.movie);
    const dialogRef = this.movieDialog.open(MovieDialogComponent,{
      width: '400px', 
      data: {obj: this.movie, actionType: actionType}
    });
    dialogRef.afterClosed().subscribe(result =>
    {
      console.log("The dialog is closed");
    });
  }

}
