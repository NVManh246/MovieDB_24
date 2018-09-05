package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Video;
import com.framgia.nvmanh.boxmovie.screen.video.VideoActivity;

public class ItemVideoViewModel {
    public ObservableField<Video> observableVideo = new ObservableField<>();

    private Context mContext;

    public ItemVideoViewModel(Context context) {
        mContext = context;
    }

    public void setVideo(Video video){
        observableVideo.set(video);
    }

    public void onClickVideo(){
        Intent intent = VideoActivity.getVideoIntent(mContext, observableVideo.get());
        mContext.startActivity(intent);
    }
}
