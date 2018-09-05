package com.framgia.nvmanh.boxmovie.screen.genres;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.data.model.GenresResults;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GenresViewModel {

    private static final int SPAN_COUT = 2;
    private static final int SPACING = 10;

    public ObservableField<GenresAdapter> observableAdapter = new ObservableField<>();
    public ObservableField<GridSpacingItemDecoration> observableDecoration = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean();
    public ObservableBoolean isError = new ObservableBoolean();

    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    public GenresViewModel(MoviesRepository moviesRepository,
                           BaseSchedulerProvider schedulerProvider) {
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
        observableDecoration.set(new GridSpacingItemDecoration(SPAN_COUT, SPACING, true));
    }

    public void start(){
        loadGenres();
    }

    private void loadGenres(){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getGenres(BuildConfig.API_KEY)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<GenresResults>() {
                    @Override
                    public void accept(GenresResults genresResults) throws Exception {
                        observableAdapter.set(new GenresAdapter(genresResults.getGenres()));
                        isLoading.set(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isError.set(true);
                        isLoading.set(false);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
