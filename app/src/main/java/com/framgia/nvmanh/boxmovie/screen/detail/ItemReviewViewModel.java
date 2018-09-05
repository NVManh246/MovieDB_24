package com.framgia.nvmanh.boxmovie.screen.detail;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Review;
import com.framgia.nvmanh.boxmovie.ultis.StringUltis;

public class ItemReviewViewModel {
    public ObservableField<Review> observableReview = new ObservableField<>();
    public ObservableField<String> observableAvatar = new ObservableField<>();

    public void setReview(Review review){
        observableReview.set(review);
        observableAvatar.set(StringUltis.getFirstCharater(review.getAuthor()));
    }
}
