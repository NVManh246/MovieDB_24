package com.framgia.nvmanh.boxmovie.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail implements Parcelable{
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
    @SerializedName("production_companies")
    private List<Company> mCompanies;
    @SerializedName("recommendations")
    private MovieResutls mRecommandations;

    public MovieDetail() {
    }

    protected MovieDetail(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mVote = in.readFloat();
        mPosterPath = in.readString();
        mBackdropPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mBudget = in.readLong();
        mRevenue = in.readLong();
        mRuntime = in.readInt();
        mStatus = in.readString();
        mGenres = new ArrayList<>();
        in.readList(mGenres, MovieDetail.class.getClassLoader());
        mCompanies = new ArrayList<>();
        in.readList(mCompanies, MovieDetail.class.getClassLoader());
    }

    public static final Creator<MovieDetail> CREATOR = new Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

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

    public List<Company> getCompanies(){
        return mCompanies;
    }

    public MovieResutls getRecommandations() {
        return mRecommandations;
    }

    public Movie getMovie() {
        Movie movie = new Movie();
        movie.setId(mId);
        movie.setTitle(mTitle);
        movie.setVote(mVote);
        movie.setPoster(mPosterPath);
        movie.setBackdrop(mBackdropPath);
        movie.setOverview(mOverview);
        movie.setReleaseDate(mReleaseDate);
        return movie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeFloat(mVote);
        parcel.writeString(mPosterPath);
        parcel.writeString(mBackdropPath);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeLong(mBudget);
        parcel.writeLong(mRevenue);
        parcel.writeInt(mRuntime);
        parcel.writeString(mStatus);
        parcel.writeList(mGenres);
        parcel.writeList(mCompanies);
    }
}
