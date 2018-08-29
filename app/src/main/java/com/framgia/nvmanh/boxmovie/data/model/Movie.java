package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("id")
    private int mId;
    @SerializedName("vote_average")
    private float mVote;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPoster;
    @SerializedName("backdrop")
    private String mBackdrop;
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("overview")
    private String mOverview;

    public Movie(int id, float vote, String title, String poster, String backdrop,
                 List<Integer> genreIds, String releaseDate, String overview) {
        mId = id;
        mVote = vote;
        mTitle = title;
        mPoster = poster;
        mBackdrop = backdrop;
        mGenreIds = genreIds;
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

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public static final class Genres{
        public static final String POPULAR = "popular";
        public static final String TOP_RATED = "top_rated";
        public static final String NOW_PLAYING = "now_playing";
        public static final String UPCOMING = "upcoming";
    }
}
