package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.local.MoviesLocalDataSource;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.DBHelper;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAOImpl;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.ActivityDetailMovieBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "movieId";

    private MovieDetailViewModel mViewModel;
    private ActivityDetailMovieBinding mBinding;

    public static Intent getMovieDetailIntent(Context context, int movieId){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        MoviesRemoteDataSource remoteDataSource = MoviesRemoteDataSource.getInstance(ApiFactory.getApi());
        MoviesDAOImpl moviesDAO = new MoviesDAOImpl(DBHelper.getInstance(this));
        MoviesLocalDataSource localDataSource = MoviesLocalDataSource.getInstance(moviesDAO);
        MoviesRepository repository = MoviesRepository.getInstace(remoteDataSource, localDataSource);
        mViewModel = new MovieDetailViewModel(this, repository, schedulerProvider);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        mBinding.setViewModel(mViewModel);
        setupToolbar(mBinding.toolbar);
        int moviedId = getMovieId(getIntent());
        if(moviedId != 0){
            mViewModel.start(moviedId);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int moviedId = getMovieId(intent);
        if(moviedId != 0){
            mViewModel.stop();
            mViewModel.start(moviedId);
            mBinding.scrollView.scrollTo (0, 0);
        }
    }

    private void setupToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int getMovieId(Intent intent){
        return intent.getIntExtra(EXTRA_MOVIE_ID, 0);
    }
}
