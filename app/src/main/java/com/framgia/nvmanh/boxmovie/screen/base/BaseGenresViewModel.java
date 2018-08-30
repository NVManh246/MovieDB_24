package com.framgia.nvmanh.boxmovie.screen.base;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BaseGenresViewModel {
    public ObservableField<MovieAdapter> observableMovieAdapter = new ObservableField<>();
    public ObservableField<String> observableGenres = new ObservableField<>();
    public ObservableBoolean isError = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(true);

    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    public BaseGenresViewModel(MoviesRepository moviesRepository,
                         BaseSchedulerProvider schedulerProvider) {
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setType(String genres){
        observableGenres.set(genres);
    }

    public void loadMovies(int page) {
        isLoading.set(true);
        Disposable disposable =
                mMoviesRepository.getMovies(observableGenres.get(), BuildConfig.API_KEY, page)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MovieResutls>() {
                    @Override
                    public void accept(MovieResutls movieResutls) throws Exception {
                        isLoading.set(false);
                        isError.set(false);
                        observableMovieAdapter.set(new MovieAdapter(movieResutls.getMovies()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isLoading.set(false);
                        isError.set(true);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
