package com.framgia.nvmanh.boxmovie.screen.overview;

import android.content.Context;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;

public class OverviewViewModel {
    public ObservableField<MovieDetail> observableMovie = new ObservableField<>();
    public ObservableField<GenresAdapter> observableGenresAdapter = new ObservableField<>();

    private Context mContext;

    public OverviewViewModel(Context context) {
        mContext = context;
    }

    public void start(MovieDetail movieDetail){
        observableMovie.set(movieDetail);
        observableGenresAdapter.set(new GenresAdapter(mContext, movieDetail.getGenres()));
    }
}
