package com.framgia.nvmanh.boxmovie.data.source.local;

import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.source.MoviesDataSource;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAO;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAOImpl;

import java.util.List;

public class MoviesLocalDataSource implements MoviesDataSource.MoviesLocalDataSource{

    private static MoviesLocalDataSource sInstance;
    private MoviesDAO mMoviesDAO;

    private MoviesLocalDataSource(MoviesDAOImpl moviesDAO){
        mMoviesDAO = moviesDAO;
    }

    public static MoviesLocalDataSource getInstance(MoviesDAOImpl moviesDAO) {
        if(sInstance == null){
            sInstance = new MoviesLocalDataSource(moviesDAO);
        }
        return sInstance;
    }

    @Override
    public List<Movie> getMovies() {
        return mMoviesDAO.getMovies();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return mMoviesDAO.addMovie(movie);
    }

    @Override
    public boolean removeMovie(int movieId) {
        return mMoviesDAO.removeMovie(movieId);
    }

    @Override
    public boolean isFavorite(int movieId) {
        return mMoviesDAO.isFavorite(movieId);
    }
}
