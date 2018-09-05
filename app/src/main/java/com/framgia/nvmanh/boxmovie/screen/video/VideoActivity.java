package com.framgia.nvmanh.boxmovie.screen.video;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Video;
import com.framgia.nvmanh.boxmovie.databinding.ActivityVideoBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class VideoActivity extends YouTubeBaseActivity {

    public static final String EXTRA_VIDEO = "video";

    private VideoViewModel mViewModel;

    public static Intent getVideoIntent(Context context, Video video){
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(EXTRA_VIDEO, video);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new VideoViewModel(this);
        ActivityVideoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_video);
        binding.setViewModel(mViewModel);
        mViewModel.start(getVideoExtra());
    }

    private Video getVideoExtra(){
        Intent intent = getIntent();
        Video video = intent.getParcelableExtra(EXTRA_VIDEO);
        return video;
    }
}
