package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("character")
    private String mCharacter;
    @SerializedName("profile_path")
    private String mProfilePath;

    public Cast(int id, String name, String character, String profilePath) {
        mId = id;
        mName = name;
        mCharacter = character;
        mProfilePath = profilePath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
