package com.framgia.nvmanh.boxmovie.data.source;

import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;

import io.reactivex.Observable;

public interface MoviesDataSource {
    interface MoviesRemoteDataSource {
        Observable<MovieResutls> getMovies(String genres, String apiKey, int page);
    }
}
