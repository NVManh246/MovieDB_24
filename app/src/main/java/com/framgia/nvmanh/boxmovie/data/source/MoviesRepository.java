package com.framgia.nvmanh.boxmovie.data.source;

import com.framgia.nvmanh.boxmovie.data.model.GenresResults;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.model.ReviewResults;
import com.framgia.nvmanh.boxmovie.data.source.local.MoviesLocalDataSource;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;

import java.util.List;

import io.reactivex.Observable;

public class MoviesRepository implements MoviesDataSource.MoviesRemoteDataSource,
        MoviesDataSource.MoviesLocalDataSource{

    private static MoviesRepository sInstace;
    private MoviesDataSource.MoviesRemoteDataSource mRemoteDataSource;
    private MoviesDataSource.MoviesLocalDataSource mLocalDataSource;

    private MoviesRepository(MoviesRemoteDataSource remoteDataSource,
                             MoviesLocalDataSource localDataSource){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static MoviesRepository getInstace(MoviesRemoteDataSource remoteDataSource,
                                              MoviesLocalDataSource localDataSource){
        if(sInstace == null){
            sInstace = new MoviesRepository(remoteDataSource, localDataSource);
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

    @Override
    public Observable<ReviewResults> getReviews(String api, int movieId, int page) {
        return mRemoteDataSource.getReviews(api, movieId, page);
    }

    public Observable<MovieResutls> getMoviesByGenres(String apiKey, int genres, int page) {
        return mRemoteDataSource.getMoviesByGenres(apiKey, genres, page);
    }

    @Override
    public Observable<MovieResutls> getMoviesByCast(String apiKey, int castId, int page) {
        return mRemoteDataSource.getMoviesByCast(apiKey, castId, page);
    }

    @Override
    public Observable<MovieResutls> getMoviesByCompany(String apiKey, int companyId, int page) {
        return mRemoteDataSource.getMoviesByCompany(apiKey, companyId, page);
    }

    @Override
    public Observable<GenresResults> getGenres(String apiKei) {
        return mRemoteDataSource.getGenres(apiKei);
    }

    @Override
    public Observable<MovieResutls> searchMoviesByTitle(String apiKey, String title, int page) {
        return mRemoteDataSource.searchMoviesByTitle(apiKey, title, page);
    }

    @Override
    public List<Movie> getMovies() {
        return mLocalDataSource.getMovies();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return mLocalDataSource.addMovie(movie);
    }

    @Override
    public boolean removeMovie(int movieId) {
        return mLocalDataSource.removeMovie(movieId);
    }

    @Override
    public boolean isFavorite(int movieId) {
        return mLocalDataSource.isFavorite(movieId);
    }
}
