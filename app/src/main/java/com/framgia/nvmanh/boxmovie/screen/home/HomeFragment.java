package com.framgia.nvmanh.boxmovie.screen.home;

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
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.local.MoviesLocalDataSource;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.DBHelper;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAOImpl;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.databinding.FragmentHomeBinding;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.SchedulerProvider;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.fragment_home, container,
                false);

        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        MoviesRemoteDataSource remoteDataSource = MoviesRemoteDataSource.getInstance(ApiFactory.getApi());
        MoviesDAOImpl moviesDAO = new MoviesDAOImpl(DBHelper.getInstance(container.getContext()));
        MoviesLocalDataSource localDataSource = MoviesLocalDataSource.getInstance(moviesDAO);
        MoviesRepository repository = MoviesRepository.getInstace(remoteDataSource, localDataSource);

        mViewModel = new HomeViewModel(getContext(), repository, schedulerProvider);
        binding.setViewModel(mViewModel);
        mViewModel.start();
        return binding.getRoot();
    }
}
