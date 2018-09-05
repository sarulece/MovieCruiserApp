import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../movie';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private snackBar: MatSnackBar,private movieService: MovieService, public dialogRef: MatDialogRef<MovieDialogComponent>, 
    @Inject(MAT_DIALOG_DATA) public data: any) 
    { 
      this.movie = data.obj;
      this.comments = data.obj.comments;
      this.actionType = data.actionType;
    }

  ngOnInit() {
    console.log(this.data);
  }

  onNoClick()
  {
    this.dialogRef.close();
  }

  onUpdateComments()
  {
    this.movie.comments = this.comments;
    this.dialogRef.close();
    this.movieService.updateMovieWatchlist(this.movie).subscribe((movie) =>
    {
      this.snackBar.open("Movie " + movie.title + " updated", "", {duration: 1000});
    });
  }

}
