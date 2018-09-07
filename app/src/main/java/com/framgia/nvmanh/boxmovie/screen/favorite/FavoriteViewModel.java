package com.framgia.nvmanh.boxmovie.screen.favorite;

import android.content.Context;

import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;

public class FavoriteViewModel {

    private Context mContext;
    private MoviesRepository mRepository;

    public FavoriteViewModel(Context context, MoviesRepository repository) {
        mContext = context;
        mRepository = repository;
    }
}
