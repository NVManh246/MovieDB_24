package com.framgia.nvmanh.boxmovie.screen.favorite;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.screen.base.MovieAdapter;
import com.framgia.nvmanh.boxmovie.screen.search.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FavoriteViewModel implements MovieAdapter.OnClickFavouriteListener{
    private static final int SPACING = 10;
    public ObservableField<MovieAdapter> observableMovieAdapter = new ObservableField<>();
    public ObservableField<RecyclerView.ItemDecoration>
            observableItemDecoration = new ObservableField<>();

    private Context mContext;
    private MoviesRepository mRepository;
    private List<Movie> mMovies;
    private MovieAdapter mMovieAdapter;

    public FavoriteViewModel(Context context, MoviesRepository repository) {
        mContext = context;
        mRepository = repository;
        observableItemDecoration.set(new VerticalSpaceItemDecoration(SPACING));
        mMovies = new ArrayList<>();
        mMovieAdapter = new MovieAdapter(mContext, mMovies);
        mMovieAdapter.setListener(this);
        observableMovieAdapter.set(mMovieAdapter);
    }

    @Override
    public void onClickFavourite(int movieId) {
        for(Movie movie : mMovies){
            if(movie.getId() == movieId){
                mMovies.remove(movie);
                break;
            }
        }
        mMovieAdapter.notifyDataSetChanged();
    }

    public void start(){
        loadMovie();
    }

    private void loadMovie(){
        mMovies.clear();
        List<Movie> movies = mRepository.getMovies();
        mMovies.addAll(movies);
        mMovieAdapter.notifyDataSetChanged();
    }
}
