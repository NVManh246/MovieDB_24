package com.framgia.nvmanh.boxmovie.screen.nowplaying;

import android.content.Context;
import android.content.Intent;

import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.screen.base.BaseGenresActivity;

public class NowPlayingActivity extends BaseGenresActivity {

    public static Intent getNowPlayingIntent(Context context){
        Intent intent = new Intent(context, NowPlayingActivity.class);
        return intent;
    }

    @Override
    public String getType() {
        return Genres.Key.NOW_PLAYING;
    }
}
