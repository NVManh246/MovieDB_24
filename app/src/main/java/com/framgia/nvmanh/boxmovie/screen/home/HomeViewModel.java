package com.framgia.nvmanh.boxmovie.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.screen.movie.MovieActivity;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomeViewModel {
    public static final int MAX_ITEM_TRENDING_MOVIE = 5;
    public ObservableField<TrendingMovieAdapter> trendingMovieAdapter = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean(true);
    public ObservableBoolean isError = new ObservableBoolean(false);

    private Context mContext;
    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    public HomeViewModel(Context context, MoviesRepository moviesRepository,
                         BaseSchedulerProvider schedulerProvider) {
        mContext = context;
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void start(){
        loadTrendingMovies();
    }

    private void loadTrendingMovies(){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getTrendingMovies(BuildConfig.API_KEY)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MovieResutls>() {
                    @Override
                    public void accept(MovieResutls movieResutls) throws Exception {
                        List<Movie> movies = movieResutls.getMovies();
                        trendingMovieAdapter.set(new TrendingMovieAdapter(movies));
                        isLoading.set(false);
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

    public void onClickPopular(){
        Intent intent = MovieActivity
                .getMovieIntent(mContext, Genres.Key.POPULAR, 0, Genres.Key.POPULAR);
        mContext.startActivity(intent);
    }

    public void onClickTopRated(){
        Intent intent = MovieActivity
                .getMovieIntent(mContext, Genres.Key.TOP_RATED, 0, Genres.Key.TOP_RATED);
        mContext.startActivity(intent);
    }

    public void onClickNowPlaying(){
        Intent intent = MovieActivity
                .getMovieIntent(mContext, Genres.Key.NOW_PLAYING, 0, Genres.Key.NOW_PLAYING);
        mContext.startActivity(intent);
    }

    public void onClickUpComing(){
        Intent intent = MovieActivity
                .getMovieIntent(mContext, Genres.Key.UPCOMING, 0, Genres.Key.UPCOMING);
        mContext.startActivity(intent);
    }

    public void retry(){
        isError.set(false);
        loadTrendingMovies();
    }
}
