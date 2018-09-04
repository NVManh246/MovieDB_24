package com.framgia.nvmanh.boxmovie.screen.upcoming;

import android.content.Context;
import android.content.Intent;

import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.screen.base.BaseGenresActivity;

public class UpComingActivity extends BaseGenresActivity {

    public static Intent getUpComingIntent(Context context){
        Intent intent = new Intent(context, UpComingActivity.class);
        return intent;
    }

    @Override
    public String getType() {
        return Genres.Key.UPCOMING;
    }
}
