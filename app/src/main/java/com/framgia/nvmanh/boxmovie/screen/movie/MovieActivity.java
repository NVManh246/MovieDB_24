package com.framgia.nvmanh.boxmovie.screen.movie;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.api.BoxMovieApi;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.ActivityMovieBinding;

import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class MovieActivity extends AppCompatActivity{

    public static final String SEARCH_TYPE = "search";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SEARCH_BY_CAST = "cast";
    public static final String SEARCH_BY_GENRES = "genres";
    public static final String DATA = "data";

    private MovieViewModel mViewModel;
    private String mSearchType;
    private String mName;
    private int mKey;

    public static Intent getMovieIntent(Context context, String searchType, int id, String name){
        Intent intent = new Intent(context, MovieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_TYPE, searchType);
        bundle.putInt(ID, id);
        bundle.putString(NAME, name);
        intent.putExtra(DATA, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxMovieApi api = ApiFactory.getApi();
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        mViewModel = new MovieViewModel(
                MoviesRepository.getInstace(MoviesRemoteDataSource.getInstance(api)),
                schedulerProvider);
        ActivityMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        binding.setViewModel(mViewModel);
        getSearchType();
        mViewModel.start(mSearchType, mKey, mName);
    }

    private void getSearchType(){
        Bundle bundle = getIntent().getBundleExtra(DATA);
        mSearchType = bundle.getString(SEARCH_TYPE);
        mKey = bundle.getInt(ID);
        mName = bundle.getString(NAME);
    }
}
