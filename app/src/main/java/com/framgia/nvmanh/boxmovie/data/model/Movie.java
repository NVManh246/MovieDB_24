package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("id")
    private int mId;
    @SerializedName("vote_average")
    private float mVote;
    @SerializedName("original_title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPoster;
    @SerializedName("backdrop")
    private String mBackdrop;
    @SerializedName("genre_ids")
    private List<Genres> mGenres;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("overview")
    private String mOverview;

    public Movie(int id, float vote, String title, String poster, String backdrop,
                 List<Genres> genres, String releaseDate, String overview) {
        mId = id;
        mVote = vote;
        mTitle = title;
        mPoster = poster;
        mBackdrop = backdrop;
        mGenres = genres;
        mReleaseDate = releaseDate;
        mOverview = overview;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public float getVote() {
        return mVote;
    }

    public void setVote(float vote) {
        mVote = vote;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public List<Genres> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genres> genres) {
        mGenres = genres;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getBackdrop() {
        return mBackdrop;
    }

    public void setBackdrop(String backdrop) {
        mBackdrop = backdrop;
    }
}
