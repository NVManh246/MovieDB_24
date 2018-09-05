package com.framgia.nvmanh.boxmovie.screen.genres;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.api.BoxMovieApi;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.FragmentGenresBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class GenresFragment extends Fragment {

    private GenresViewModel mViewModel;

    public static GenresFragment newInstance() {
        GenresFragment fragment = new GenresFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGenresBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.fragment_genres, container,
                false);
        BoxMovieApi api = ApiFactory.getApi();
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        mViewModel = new GenresViewModel(getContext(),
                MoviesRepository.getInstace(MoviesRemoteDataSource.getInstance(api)),
                schedulerProvider);
        binding.setViewModel(mViewModel);
        mViewModel.start();
        return binding.getRoot();
    }
}
