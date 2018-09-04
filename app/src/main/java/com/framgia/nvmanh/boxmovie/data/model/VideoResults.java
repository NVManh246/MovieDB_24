package com.framgia.nvmanh.boxmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResults {
    @SerializedName("results")
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }
}
