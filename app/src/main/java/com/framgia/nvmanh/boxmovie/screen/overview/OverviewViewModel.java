package com.framgia.nvmanh.boxmovie.screen.overview;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.screen.search.VerticalSpaceItemDecoration;

public class OverviewViewModel {
    private static final int SPACING = 10;

    public ObservableField<MovieDetail> observableMovie = new ObservableField<>();
    public ObservableField<GenresAdapter> observableGenresAdapter = new ObservableField<>();
    public ObservableField<CompanyAdapter> observableCompanyAdapter = new ObservableField<>();

    private Context mContext;

    public OverviewViewModel(Context context) {
        mContext = context;
    }

    public void start(MovieDetail movieDetail){
        observableMovie.set(movieDetail);
        observableGenresAdapter.set(new GenresAdapter(mContext, movieDetail.getGenres()));
        observableCompanyAdapter.set(new CompanyAdapter(mContext, movieDetail.getCompanies()));
    }

    public RecyclerView.ItemDecoration getItemDecoration(){
        return new VerticalSpaceItemDecoration(SPACING);
    }
}
