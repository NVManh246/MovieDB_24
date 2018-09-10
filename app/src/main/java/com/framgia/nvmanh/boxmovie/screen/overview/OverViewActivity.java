package com.framgia.nvmanh.boxmovie.screen.overview;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.databinding.ActivityOverviewBinding;

public class OverViewActivity extends AppCompatActivity{

    public static final String EXTRA_MOVIE = "movie";

    private OverviewViewModel mViewModel;

    public static Intent getOverViewIntent(Context context, MovieDetail movieDetail){
        Intent intent = new Intent(context, OverViewActivity.class);
        intent.putExtra(EXTRA_MOVIE, movieDetail);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOverviewBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_overview);
        mViewModel = new OverviewViewModel(this);
        binding.setViewModel(mViewModel);
        setupToolbar(binding.toolbar);
        MovieDetail movieDetail = getMovieDetail();
        mViewModel.start(movieDetail);
    }

    private void setupToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private MovieDetail getMovieDetail(){
        Intent intent = getIntent();
        MovieDetail movieDetail = intent.getParcelableExtra(EXTRA_MOVIE);
        return movieDetail;
    }
}
