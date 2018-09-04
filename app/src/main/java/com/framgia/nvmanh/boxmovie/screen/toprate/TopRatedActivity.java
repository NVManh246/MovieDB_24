package com.framgia.nvmanh.boxmovie.screen.toprate;

import android.content.Context;
import android.content.Intent;

import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.screen.base.BaseGenresActivity;

public class TopRatedActivity extends BaseGenresActivity{

    public static Intent getTopRatedIntent(Context context){
        Intent intent = new Intent(context, TopRatedActivity.class);
        return intent;
    }

    @Override
    public String getType() {
        return Genres.Key.TOP_RATED;
    }
}
