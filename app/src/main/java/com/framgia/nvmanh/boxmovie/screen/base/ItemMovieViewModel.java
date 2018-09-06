package com.framgia.nvmanh.boxmovie.screen.base;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Movie;

public class ItemMovieViewModel {
    public ObservableField<Movie> observableMovie = new ObservableField<>();

    public void setMovie(Movie movie){
        observableMovie.set(movie);
    }
}
