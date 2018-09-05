package com.framgia.nvmanh.boxmovie.screen.detail;

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
import com.framgia.nvmanh.boxmovie.databinding.ActivityDetailMovieBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "movieId";

    private MovieDetailViewModel mViewModel;

    public static Intent getMovieDetailIntent(Context context, int movieId){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxMovieApi api = ApiFactory.getApi();
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        mViewModel = new MovieDetailViewModel(this,
                MoviesRepository.getInstace(MoviesRemoteDataSource.getInstance(api)),
                schedulerProvider);
        ActivityDetailMovieBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        binding.setViewModel(mViewModel);

        mViewModel.start(getMovieId());
    }

    private int getMovieId(){
        Intent intent = getIntent();
        return intent.getIntExtra(EXTRA_MOVIE_ID, 0);
    }
}
