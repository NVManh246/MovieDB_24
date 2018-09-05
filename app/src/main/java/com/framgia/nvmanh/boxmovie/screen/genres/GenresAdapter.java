package com.framgia.nvmanh.boxmovie.screen.genres;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.databinding.ItemGenresBinding;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private Context mContext;
    private List<Genres> mGenres;

    public GenresAdapter(Context context, List<Genres> genres) {
        mContext = context;
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenresBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_genres, parent,
                false);

        return new ViewHolder(mContext, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ItemGenresViewModel mViewModel;
        private ItemGenresBinding mBinding;

        public ViewHolder(Context context, ItemGenresBinding binding) {
            super(binding.getRoot());
            mContext = context;
            mBinding = binding;
            mViewModel = new ItemGenresViewModel(context);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Genres genres){
            mViewModel.setGenres(genres);
            mBinding.executePendingBindings();
        }
    }
}
