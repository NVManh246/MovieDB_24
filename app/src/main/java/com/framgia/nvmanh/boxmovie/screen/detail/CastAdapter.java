package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
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

    private Context mContext;
    private List<Cast> mCasts;

    public CastAdapter(Context context, List<Cast> casts) {
        mContext = context;
        mCasts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_cast, parent,
                false);
        return new ViewHolder(mContext, binding);
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
        private Context mContext;
        private ItemCastBinding mBinding;
        private ItemCastViewModel mViewModel;

        public ViewHolder(Context context, ItemCastBinding binding) {
            super(binding.getRoot());
            mContext = context;
            mBinding = binding;
            mViewModel = new ItemCastViewModel(mContext);
            mBinding.setViewModel(mViewModel);
        }

        private void bindView(Cast cast) {
            mViewModel.setCast(cast);
        }
    }
}
