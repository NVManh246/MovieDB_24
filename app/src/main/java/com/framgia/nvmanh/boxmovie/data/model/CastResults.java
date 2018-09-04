package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResults {
    @SerializedName("cast")
    private List<Cast> mCasts;

    public List<Cast> getCasts() {
        return mCasts;
    }
}
