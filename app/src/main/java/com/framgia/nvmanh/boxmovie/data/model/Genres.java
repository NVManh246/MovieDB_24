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
}
