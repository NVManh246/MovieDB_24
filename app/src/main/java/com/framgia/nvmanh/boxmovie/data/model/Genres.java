package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

public class Genres {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    public Genres(int id, String name) {
        mId = id;
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public static final class Key {
        public static final String POPULAR = "popular";
        public static final String TOP_RATED = "top_rated";
        public static final String NOW_PLAYING = "now_playing";
        public static final String UPCOMING = "upcoming";
    }
}
