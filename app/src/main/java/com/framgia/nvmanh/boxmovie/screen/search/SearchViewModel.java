package com.framgia.nvmanh.boxmovie.screen.search;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

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
import io.reactivex.functions.Consumer;

public class SearchViewModel {
    private static final int SPACING = 10;
    private static final int NUMBER_ONE = 1;

    public ObservableField<MovieAdapter> observableMovieAdapter = new ObservableField<>();
    public ObservableField<RecyclerView.ItemDecoration>
            observableItemDecoration = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean();
    public ObservableBoolean isError = new ObservableBoolean();

    private Context mContext;
    private Activity mActivity;
    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private List<Movie> mMovies;
    private MovieAdapter mMovieAdapter;
    private int mPage;

    public SearchViewModel(Context context, Activity activity, MoviesRepository moviesRepository,
                                BaseSchedulerProvider schedulerProvider) {
        mContext = context;
        mActivity = activity;
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
        mMovies = new ArrayList<>();
        mMovieAdapter = new MovieAdapter(mContext, mMovies);
        observableMovieAdapter.set(mMovieAdapter);
        observableItemDecoration.set(new VerticalSpaceItemDecoration(SPACING));
        mPage = NUMBER_ONE;
    }

    public void start(){}

    public void searchMovieByTitle(String title, int page){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.searchMoviesByTitle(BuildConfig.API_KEY, title, page)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MovieResutls>() {
                    @Override
                    public void accept(MovieResutls movieResutls) throws Exception {
                        mMovies.clear();
                        mMovies.addAll(movieResutls.getMovies());
                        mMovieAdapter.notifyDataSetChanged();
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

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchMovieByTitle(s.toString(), mPage);
    }

    public void onClickBack(){
        mActivity.finish();
    }
}
