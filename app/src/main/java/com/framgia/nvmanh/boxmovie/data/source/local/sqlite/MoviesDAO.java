package com.framgia.nvmanh.boxmovie.data.source.local.sqlite;

import com.framgia.nvmanh.boxmovie.data.model.Movie;

import java.util.List;

public interface MoviesDAO {
    List<Movie> getMovies();
    boolean addMovie(Movie movie);
    boolean removeMovie(int movieId);
    boolean isFavorite(int movieId);
}
