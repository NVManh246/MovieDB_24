package com.framgia.nvmanh.boxmovie.screen.video;

import android.app.Activity;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Video;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class VideoViewModel {
    private static final int ERROR_CODE = 111;

    public ObservableField<Video> observableVideo = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public ObservableField<YouTubePlayer.OnInitializedListener> initListener = new ObservableField<>();

    private Activity mActivity;

    public VideoViewModel(Activity activity) {
        mActivity = activity;
    }

    public void start(Video video){
        loadVideo(video);
    }

    private void loadVideo(final Video video){
        observableVideo.set(video);
        isLoading.set(true);
        initListener.set(new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(video.getKey());
                    youTubePlayer.setFullscreen(true);
                    youTubePlayer.setShowFullscreenButton(false);
                    isLoading.set(false);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youTubeInitializationResult.getErrorDialog(mActivity, ERROR_CODE).show();
            }
        });
    }
}
