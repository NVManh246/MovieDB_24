package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Cast;
import com.framgia.nvmanh.boxmovie.screen.movie.MovieActivity;

public class ItemCastViewModel {

    public ObservableField<Cast> observableCast = new ObservableField<>();

    private Context mContext;

    public ItemCastViewModel(Context context) {
        mContext = context;
    }

    public void setCast(Cast cast){
        observableCast.set(cast);
    }

    public void onClickItemCast(){
        Cast cast = observableCast.get();
        Intent intent = MovieActivity.getMovieIntent(mContext,
                MovieActivity.SEARCH_BY_CAST, cast.getId(), cast.getName());
        mContext.startActivity(intent);
    }
}
