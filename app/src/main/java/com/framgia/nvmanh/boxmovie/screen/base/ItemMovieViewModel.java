package com.framgia.nvmanh.boxmovie.screen.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.api.ApiFactory;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.data.source.local.MoviesLocalDataSource;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.DBHelper;
import com.framgia.nvmanh.boxmovie.data.source.local.sqlite.MoviesDAOImpl;
import com.framgia.nvmanh.boxmovie.data.source.remote.MoviesRemoteDataSource;
import com.framgia.nvmanh.boxmovie.screen.detail.MovieDetailActivity;
import com.framgia.nvmanh.boxmovie.ultis.StringUltis;

public class ItemMovieViewModel {
    public ObservableField<Movie> observableMovie = new ObservableField<>();

    private Context mContext;
    private MoviesRepository mRepository;
    private MovieAdapter.OnClickFavouriteListener mListener;

    public ItemMovieViewModel(Context context, MovieAdapter.OnClickFavouriteListener listener) {
        mContext = context;
        MoviesRemoteDataSource remoteDataSource = MoviesRemoteDataSource.getInstance(ApiFactory.getApi());
        MoviesDAOImpl moviesDAO = new MoviesDAOImpl(DBHelper.getInstance(mContext));
        MoviesLocalDataSource localDataSource = MoviesLocalDataSource.getInstance(moviesDAO);
        mRepository = MoviesRepository.getInstace(remoteDataSource, localDataSource);
        mListener = listener;
    }

    public void setMovie(Movie movie){
        observableMovie.set(movie);
    }

    public void onClickItemMovie(){
        Intent intent = MovieDetailActivity.getMovieDetailIntent(mContext,
                observableMovie.get().getId());
        mContext.startActivity(intent);
    }

    public String getYear(){
        return StringUltis.getYear(observableMovie.get().getReleaseDate());
    }

    public void onClickPopupMenu(View view){
        Movie movie = observableMovie.get();
        boolean isFavourite = mRepository.isFavorite(movie.getId());
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        popupMenu.getMenuInflater().inflate(R.menu.favourite_popup_menu, popupMenu.getMenu());
        Menu menu = popupMenu.getMenu();
        if(isFavourite){
            menu.findItem(R.id.menu_favourite)
                    .setTitle(mContext.getString(R.string.title_remove_to_wishlist));
        } else {
            menu.findItem(R.id.menu_favourite)
                    .setTitle(mContext.getString(R.string.title_add_to_wishlist));
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(isFavourite){
                    if(mListener != null) {
                        mListener.onClickFavourite(movie.getId());
                    }
                    mRepository.removeMovie(movie.getId());
                    showMsg(mContext.getString(R.string.msg_remove_to_wishlist));
                } else {
                    mRepository.addMovie(movie);
                    showMsg(mContext.getString(R.string.msg_add_to_wishlist));
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void showMsg(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
