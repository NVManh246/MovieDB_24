package com.framgia.nvmanh.boxmovie.data.source;

import com.framgia.nvmanh.boxmovie.data.model.GenresResults;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.model.ReviewResults;

import java.util.List;

import io.reactivex.Observable;

public interface MoviesDataSource {
    interface MoviesRemoteDataSource {
        Observable<MovieResutls> getMovies(String genres, String apiKey, int page);
        Observable<MovieResutls> getTrendingMovies(String apiKey);
        Observable<MovieDetail> getMovieDetail(String apiKey, int movieId);
        Observable<ReviewResults> getReviews(String api, int movieId, int page);
        Observable<GenresResults> getGenres(String apiKei);
        Observable<MovieResutls> getMoviesByGenres(String apiKey, int genresId, int page);
        Observable<MovieResutls> getMoviesByCast(String apiKey, int castId, int page);
        Observable<MovieResutls> searchMoviesByTitle(String apiKey, String title, int page);
    }

    interface MoviesLocalDataSource {
        List<Movie> getMovies();
        boolean addMovie(Movie movie);
        boolean removeMovie(int movieId);
        boolean isFavorite(int movieId);
    }
}
