package com.framgia.nvmanh.boxmovie.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.databinding.ItemTrendingMovieBinding;
import com.framgia.nvmanh.boxmovie.screen.base.ItemMovieViewModel;

import java.util.List;

public class TrendingMovieAdapter extends PagerAdapter {

    private List<Movie> mMovies;
    private ItemMovieViewModel mViewModel;

    public TrendingMovieAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mViewModel = new ItemMovieViewModel();
        ItemTrendingMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.item_trending_movie, container,
                true);
        binding.setViewModel(mViewModel);
        mViewModel.setMovie(mMovies.get(position));
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return mMovies != null ? HomeViewModel.MAX_ITEM_TRENDING_MOVIE : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
