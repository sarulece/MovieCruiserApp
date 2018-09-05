import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Movie } from './movie';
import  { Observable } from 'rxjs/Observable'

@Injectable()
export class MovieService {
  tmdbEndPoint: string;
  posterPathPrefix : string;
  apiKey : string;
  watchlistEndPoint: string;
  searchEndPoint : string;

  constructor(private http : HttpClient) { 
    this.apiKey = "api_key=a8e42f8e3fb3bb3e24679a00d1063dc6";
    this.tmdbEndPoint = 'https://api.themoviedb.org/3/movie';
    this.posterPathPrefix = 'https://image.tmdb.org/t/p/w500';
    this.watchlistEndPoint = 'http://localhost:8081/api/movies';
    this.searchEndPoint = 'https://api.themoviedb.org/3/search/movie?';
  }

  getPopularMovies(page : number = 1) : Observable<Array<Movie>> {
    return this.getMovies("popular", page);
  }

  getTopRatedMovies(page : number = 1) : Observable<Array<Movie>> {
    return this.getMovies("top_rated", page);
  }

  getMovies(type : string, page : number = 1) : Observable<Array<Movie>> {
    const endPoint = `${this.tmdbEndPoint}/${type}?${this.apiKey}&page=${page}`
    return this.http.get(endPoint).pipe(
      map(this.pickMovieResults),
      map(this.transformMoviePosterPath.bind(this))
    );
  }

  transformMoviePosterPath(movies : Array<Movie>): Array<Movie> {
    return movies.map( movie => {
      console.log("Movie: " + movie);
      movie.poster_path = `${this.posterPathPrefix}${movie.poster_path}`;
      return movie;
    });
  }
  pickMovieResults(response) : Array<Movie>{
    return response['results'];
  }

  searchMovies(searchKey) : Observable<Array<Movie>>
  {
    if(searchKey.length > 0)
    {
      const endPointUrl = `${this.searchEndPoint}${this.apiKey}&page=1&query=${searchKey}`;
    return this.http.get(endPointUrl).pipe(
      map(this.pickMovieResults),
      map(this.transformMoviePosterPath.bind(this))
    );
    }   
  }

  addMovieToWatchlist(movie: Movie)
  {
    console.log("Add Movie API: " + movie);
    return this.http.post(this.watchlistEndPoint, movie);
  }

  getMovieWatchlist() : Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.watchlistEndPoint);
  }

  deleteMovieFromWatchlist(movie: Movie)
  {
    const url = `${this.watchlistEndPoint}/${movie.id}`;
    return this.http.delete(url, {responseType: 'text'});
  }

  updateMovieWatchlist(movie) : Observable<Movie>
  {
    const url = `${this.watchlistEndPoint}/${movie.id}`;
    return this.http.put<Movie>(url, movie);
  }

}
