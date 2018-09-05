import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from '@angular/router/src/config';
import { MovieContainerComponent } from './components/movie-container/movie-container.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { MovieSearchComponent } from './components/movie-search/movie-search.component';
import { AuthguardService } from '../../authGuard.service';

const movieRoutes : Routes = [
    {
        path: 'movies',
        children: [
            {
                path : '',
                redirectTo : '/movies/popular',
                pathMatch : 'full',
                canActivate: [AuthguardService]
            },
            {
                path : 'popular',
                component: TmdbContainerComponent,
                canActivate: [AuthguardService],
                data :
                {
                    movieType : 'popular'
                }
            },
            {
                path : 'top_rated',
                component: TmdbContainerComponent,
                canActivate: [AuthguardService],
                data :
                {
                    movieType : 'top_rated'
                }
            },
            {
                path: 'watchlist',
                component: WatchlistComponent,
                canActivate: [AuthguardService]
            },
            {
                path: 'search',
                component: MovieSearchComponent,
                canActivate: [AuthguardService]
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(movieRoutes)
    ],
    declarations: [ ],
    exports: [ RouterModule],
    providers: [],
  })
  export class MovieRouterModule { }