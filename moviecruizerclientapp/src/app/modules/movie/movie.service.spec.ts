import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { HttpClientModule, HttpClient } from  '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MovieService } from './movie.service';
import { Observable } from 'rxjs';

const testConfig={
      movies:[{
          id: '100',
          title: 'Titanic',
          poster_path: 'abc.com',
          overview: 'content',
          release_date: '2010-01-01',
          comments: 'good movie'
      }],
    movie:{
        id: '100',
        title: 'Titanic',
        poster_path: 'abc.com',
        overview: 'content',
        release_date: '2010-01-01',
        comments: 'good movie'
    }
}

describe('MovieService', () => {
  let movieService: MovieService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [MovieService]
    });
    movieService = TestBed.get(MovieService);
  });

  it('should be created', inject([MovieService], (service: MovieService) => {
    expect(service).toBeTruthy();
  }));

  it('should add movie to watchlist', fakeAsync(() => {

    let  data = testConfig.movie;
    inject([MovieService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(movieService.watchlistEndPoint);
        expect(mockReq.request.url).toEqual(movieService.watchlistEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    movieService.addMovieToWatchlist(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should update movie to watchlist', fakeAsync(() => {

    let  data = testConfig.movie;
    inject([MovieService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(movieService.watchlistEndPoint);
        expect(mockReq.request.url).toEqual(movieService.watchlistEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    movieService.updateMovieWatchlist(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should delete movie from watchlist', fakeAsync(() => {

    let  data = testConfig.movie;
    inject([MovieService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(movieService.watchlistEndPoint);
        expect(mockReq.request.url).toEqual(movieService.watchlistEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    movieService.deleteMovieFromWatchlist(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should fetch watchlist movies', fakeAsync(() => {

    let  data = testConfig.movies;
    inject([MovieService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(movieService.watchlistEndPoint);
        expect(mockReq.request.url).toEqual(movieService.watchlistEndPoint, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    movieService.getMovieWatchlist().subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

});
