package com.framgia.nvmanh.boxmovie.screen.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.local.MoviesLocalDataSource;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.DBHelper;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAOImpl;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.ActivityBaseMovieBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public abstract class BaseGenresActivity extends AppCompatActivity {

    protected BaseGenresViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        MoviesRemoteDataSource remoteDataSource = MoviesRemoteDataSource.getInstance(ApiFactory.getApi());
        MoviesDAOImpl moviesDAO = new MoviesDAOImpl(DBHelper.getInstance(this));
        MoviesLocalDataSource localDataSource = MoviesLocalDataSource.getInstance(moviesDAO);
        MoviesRepository repository = MoviesRepository.getInstace(remoteDataSource, localDataSource);

        mViewModel = new BaseGenresViewModel(this, repository, schedulerProvider);
        mViewModel.setType(getType());

        ActivityBaseMovieBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_base_movie);
        setupToolbar(binding.toolbar);
        binding.setViewModel(mViewModel);
        mViewModel.start();
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

    public abstract String getType();
}
