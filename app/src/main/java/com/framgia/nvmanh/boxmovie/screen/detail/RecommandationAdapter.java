package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.databinding.ItemMovieVerticalBinding;
import com.framgia.nvmanh.boxmovie.screen.base.ItemMovieViewModel;

import java.util.List;

public class RecommandationAdapter extends RecyclerView.Adapter<RecommandationAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public RecommandationAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieVerticalBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_vertical, parent, false);
        return new ViewHolder(mContext, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieViewModel mViewModel;
        private ItemMovieVerticalBinding mBinding;

        public ViewHolder(Context context, ItemMovieVerticalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel(context, null);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Movie movie){
            mViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }
}
