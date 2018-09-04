package com.framgia.nvmanh.boxmovie.data.source;

import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;

import io.reactivex.Observable;

public interface MoviesDataSource {
    interface MoviesRemoteDataSource {
        Observable<MovieResutls> getMovies(String genres, String apiKey, int page);
        Observable<MovieResutls> getTrendingMovies(String apiKey);
        Observable<MovieDetail> getMovieDetail(String apiKey, int movieId);
    }
}
