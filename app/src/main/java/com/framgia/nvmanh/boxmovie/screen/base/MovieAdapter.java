package com.framgia.nvmanh.boxmovie.screen.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.databinding.ItemMovieBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;
    private OnClickFavouriteListener mListener;

    public MovieAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie, parent, false);
        return new ViewHolder(mContext, binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void setListener(OnClickFavouriteListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieViewModel mViewModel;
        private ItemMovieBinding mBinding;

        public ViewHolder(Context context, ItemMovieBinding binding, OnClickFavouriteListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel(context, listener);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Movie movie){
            mViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public interface OnClickFavouriteListener {
        void onClickFavourite(int movieId);
    }
}
