import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule} from '@angular/common/http';
import { MatCardModule } from '@angular/material/card'

import { MovieThumbnailComponent } from './components/movie-thumbnail/movie-thumbnail.component';
import { MovieService } from './movie.service';
import { MovieContainerComponent } from './components/movie-container/movie-container.component';
import { MovieRouterModule } from './movie-router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MatButtonModule, MatSnackBarModule } from '@angular/material';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule} from '@angular/forms';
import { MovieSearchComponent } from './components/movie-search/movie-search.component';
import { Movie } from './movie';
import { TokenInterceptorService } from './interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule,
    MatInputModule,
  ],
  declarations: [ MovieThumbnailComponent
    , MovieContainerComponent
    , WatchlistComponent, TmdbContainerComponent, MovieDialogComponent, MovieSearchComponent],
  exports: [ MovieThumbnailComponent, MovieRouterModule, MovieDialogComponent,
            ],
  providers: [MovieService, 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  entryComponents: [MovieDialogComponent],
})
export class MovieModule { }
