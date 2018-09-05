package com.framgia.nvmanh.boxmovie.screen.detail;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Video;

public class ItemVideoViewModel {
    public ObservableField<Video> observableVideo = new ObservableField<>();

    public void setVideo(Video video){
        observableVideo.set(video);
    }
}
