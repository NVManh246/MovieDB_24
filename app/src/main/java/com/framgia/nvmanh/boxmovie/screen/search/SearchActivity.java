package com.framgia.nvmanh.boxmovie.screen.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.api.BoxMovieApi;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.ActivitySearchBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class SearchActivity extends AppCompatActivity {

    private SearchViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BoxMovieApi api = ApiFactory.getApi();
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        mViewModel = new SearchViewModel(this,
                MoviesRepository.getInstace(MoviesRemoteDataSource.getInstance(api)),
                schedulerProvider);
        ActivitySearchBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewModel(mViewModel);
        mViewModel.start();
    }
}
