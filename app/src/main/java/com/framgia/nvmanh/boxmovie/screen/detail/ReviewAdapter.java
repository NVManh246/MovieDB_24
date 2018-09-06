package com.framgia.nvmanh.boxmovie.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Review;
import com.framgia.nvmanh.boxmovie.databinding.ItemReviewBinding;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{
    private List<Review> mReviews;

    public ReviewAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_review, parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews != null ? mReviews.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemReviewBinding mBinding;
        private ItemReviewViewModel mViewModel;
        public ViewHolder(ItemReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemReviewViewModel();
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Review review){
            mViewModel.setReview(review);
        }
    }
}
