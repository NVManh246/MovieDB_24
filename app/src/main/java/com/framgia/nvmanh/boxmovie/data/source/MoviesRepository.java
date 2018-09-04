package com.framgia.nvmanh.boxmovie.data.source;

import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;

import io.reactivex.Observable;

public class MoviesRepository implements MoviesDataSource.MoviesRemoteDataSource {

    private static MoviesRepository sInstace;
    private MoviesDataSource.MoviesRemoteDataSource mRemoteDataSource;

    private MoviesRepository(MoviesRemoteDataSource remoteDataSource){
        mRemoteDataSource = remoteDataSource;
    }

    public static MoviesRepository getInstace(MoviesRemoteDataSource remoteDataSource){
        if(sInstace == null){
            sInstace = new MoviesRepository(remoteDataSource);
        }
        return sInstace;
    }

    @Override
    public Observable<MovieResutls> getMovies(String genres, String apiKey, int page) {
        return mRemoteDataSource.getMovies(genres, apiKey, page);
    }

    @Override
    public Observable<MovieResutls> getTrendingMovies(String apiKey) {
        return mRemoteDataSource.getTrendingMovies(apiKey);
    }

    @Override
    public Observable<MovieDetail> getMovieDetail(String apiKey, int movieId) {
        return mRemoteDataSource.getMovieDetail(apiKey, movieId);
    }
}
