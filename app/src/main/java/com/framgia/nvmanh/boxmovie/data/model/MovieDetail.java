package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_average")
    private float mVote;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("budget")
    private long mBudget;
    @SerializedName("genres")
    private List<Genres> mGenres;
    @SerializedName("revenue")
    private long mRevenue;
    @SerializedName("runtime")
    private int mRuntime;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("casts")
    private CastResults mCastResults;
    @SerializedName("videos")
    private VideoResults mVideoResults;
    @SerializedName("reviews")
    private ReviewResults mReviewsResults;

    public MovieDetail() {
    }

    public void setId(int id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setVote(float vote) {
        mVote = vote;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setBudget(long budget) {
        mBudget = budget;
    }

    public void setGenres(List<Genres> genres) {
        mGenres = genres;
    }

    public void setRevenue(long revenue) {
        mRevenue = revenue;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public void setCastResults(CastResults castResults) {
        mCastResults = castResults;
    }

    public void setVideoResults(VideoResults videoResults) {
        mVideoResults = videoResults;
    }

    public void setReviewsResults(ReviewResults reviewsResults) {
        mReviewsResults = reviewsResults;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getVote() {
        return mVote;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public long getBudget() {
        return mBudget;
    }

    public List<Genres> getGenres() {
        return mGenres;
    }

    public long getRevenue() {
        return mRevenue;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public String getStatus() {
        return mStatus;
    }

    public CastResults getCastResults() {
        return mCastResults;
    }

    public VideoResults getVideoResults() {
        return mVideoResults;
    }

    public ReviewResults getReviewsResults() {
        return mReviewsResults;
    }
}
