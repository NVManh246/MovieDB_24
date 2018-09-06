package com.framgia.nvmanh.boxmovie.screen.genres;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Genres;

import io.reactivex.Observable;

public class ItemGenresViewModel {
    public ObservableField<Genres> mGenres = new ObservableField<>();

    public void setGenres(Genres genres){
        mGenres.set(genres);
    }
}
