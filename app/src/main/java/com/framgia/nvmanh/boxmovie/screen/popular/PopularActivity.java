package com.framgia.nvmanh.boxmovie.screen.popular;

import android.content.Context;
import android.content.Intent;

import com.framgia.nvmanh.boxmovie.data.model.Genres;
import com.framgia.nvmanh.boxmovie.screen.base.BaseGenresActivity;

public class PopularActivity extends BaseGenresActivity {

    public static Intent getPopularIntent(Context context){
        Intent intent = new Intent(context, PopularActivity.class);
        return intent;
    }

    @Override
    public String getType() {
        return Genres.Key.POPULAR;
    }
}
