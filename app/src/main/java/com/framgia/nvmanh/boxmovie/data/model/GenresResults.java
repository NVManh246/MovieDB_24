package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResults {
    @SerializedName("genres")
    private List<Genres> mGenres;

    public List<Genres> getGenres() {
        return mGenres;
    }
}
