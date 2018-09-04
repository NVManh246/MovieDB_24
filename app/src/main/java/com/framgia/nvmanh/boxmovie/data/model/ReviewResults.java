package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResults {
    @SerializedName("results")
    private List<Review> mReviews;

    public List<Review> getReviews() {
        return mReviews;
    }
}
