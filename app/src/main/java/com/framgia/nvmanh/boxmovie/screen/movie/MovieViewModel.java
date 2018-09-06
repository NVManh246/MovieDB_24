package com.framgia.nvmanh.boxmovie.screen.movie;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.screen.base.MovieAdapter;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MovieViewModel {
    public ObservableField<MovieAdapter> obervableMovieAdapter = new ObservableField<>();
    public ObservableField<String> obervableTitle = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean();
    public ObservableBoolean isError = new ObservableBoolean();

    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private List<Movie> mMovies;
    private MovieAdapter mMovieAdapter;
    private String mSearchType;
    private int mId;
    private int mPage = 1;

    public MovieViewModel(MoviesRepository moviesRepository,
                           BaseSchedulerProvider schedulerProvider) {
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
        mMovies = new ArrayList<>();
        mMovieAdapter = new MovieAdapter(mMovies);
        obervableMovieAdapter.set(mMovieAdapter);
    }

    public void start(String searchType, int id, String name){
        mSearchType = searchType;
        mId = id;
        obervableTitle.set(name);
        loadMovie(mSearchType, mId, mPage);
    }

    private void loadMovie(String searchType, int id, int page){
        isLoading.set(true);
        if(searchType.equals(MovieActivity.SEARCH_BY_GENRES)){
            loadMoviesByGenres(id, page);
        } else {
            loadMoviesByCast(id, page);
        }
    }

    private void loadMoviesByGenres(int genresId, int page){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getMoviesByGenres(BuildConfig.API_KEY, genresId, page)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(response -> handlerResponse(response),
                        error -> handlerError(error));
        mCompositeDisposable.add(disposable);
    }

    private void loadMoviesByCast(int castId, int page){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getMoviesByCast(BuildConfig.API_KEY, castId, page)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(response -> handlerResponse(response),
                        error -> handlerError(error));
        mCompositeDisposable.add(disposable);
    }

    private void handlerResponse(MovieResutls response) {
        int oldSize = mMovies.size();
        mMovies.addAll(response.getMovies());
        mMovieAdapter.notifyItemRangeChanged(oldSize, mMovies.size());
        isLoading.set(false);
    }

    private void handlerError(Throwable error) {
        isError.set(true);
        isLoading.set(false);
    }

    public void onLoadMore(){
        loadMovie(mSearchType, mId, ++mPage);
    }
}
