package com.framgia.nvmanh.boxmovie.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Cast;
import com.framgia.nvmanh.boxmovie.databinding.ItemCastBinding;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<Cast> mCasts;

    public CastAdapter(List<Cast> casts) {
        mCasts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_cast, parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(mCasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCasts != null ? mCasts.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCastBinding mBinding;
        private ItemCastViewModel mViewModel;

        public ViewHolder(ItemCastBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemCastViewModel();
            mBinding.setViewModel(mViewModel);
        }

        private void bindView(Cast cast) {
            mViewModel.setCast(cast);
        }
    }
}
