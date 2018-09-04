package com.framgia.nvmanh.boxmovie.data.source.remote;

import com.framgia.nvmanh.boxmovie.data.api.BoxMovieApi;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.source.MoviesDataSource;

import io.reactivex.Observable;

public class MoviesRemoteDataSource implements MoviesDataSource.MoviesRemoteDataSource {

    private static MoviesRemoteDataSource sInstance;
    private BoxMovieApi mBoxMovieApi;

    public MoviesRemoteDataSource(BoxMovieApi boxMovieApi) {
        mBoxMovieApi = boxMovieApi;
    }

    public static MoviesRemoteDataSource getInstance(BoxMovieApi boxMovieApi){
        if(sInstance == null){
            sInstance = new MoviesRemoteDataSource(boxMovieApi);
        }
        return sInstance;
    }

    @Override
    public Observable<MovieResutls> getMovies(String genres, String apiKey, int page) {
        return mBoxMovieApi.getMovies(genres, apiKey, page);
    }

    @Override
    public Observable<MovieResutls> getTrendingMovies(String apiKey) {
        return mBoxMovieApi.getTrendingMovies(apiKey);
    }

    @Override
    public Observable<MovieDetail> getMovieDetail(String apiKey, int movieId) {
        return mBoxMovieApi.getMovieDetail(movieId, apiKey);
    }
}
