package com.framgia.nvmanh.boxmovie.screen.genres;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.screen.movie.MovieActivity;

public class ItemGenresViewModel {
    public ObservableField<Genres> mGenres = new ObservableField<>();

    private Context mContext;

    public ItemGenresViewModel(Context context) {
        mContext = context;
    }

    public void setGenres(Genres genres){
        mGenres.set(genres);
    }

    public void onClickItemGenres(){
        Genres genres = mGenres.get();
        Intent intent = MovieActivity.getMovieIntent(mContext,
                MovieActivity.SEARCH_BY_GENRES, genres.getId(), genres.getName());
        mContext.startActivity(intent);
    }
}
