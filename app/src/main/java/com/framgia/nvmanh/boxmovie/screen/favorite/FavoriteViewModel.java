package com.framgia.nvmanh.boxmovie.screen.favorite;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.screen.base.MovieAdapter;
import com.framgia.nvmanh.boxmovie.screen.search.VerticalSpaceItemDecoration;

import java.util.List;

public class FavoriteViewModel {
    private static final int SPACING = 10;
    public ObservableField<MovieAdapter> observableMovieAdapter = new ObservableField<>();
    public ObservableField<RecyclerView.ItemDecoration>
            observableItemDecoration = new ObservableField<>();

    private Context mContext;
    private MoviesRepository mRepository;

    public FavoriteViewModel(Context context, MoviesRepository repository) {
        mContext = context;
        mRepository = repository;
        observableItemDecoration.set(new VerticalSpaceItemDecoration(SPACING));
    }

    public void start(){
        loadMovie();
    }

    private void loadMovie(){
        List<Movie> movies = mRepository.getMovies();
        observableMovieAdapter.set(new MovieAdapter(mContext, movies));
    }
}
