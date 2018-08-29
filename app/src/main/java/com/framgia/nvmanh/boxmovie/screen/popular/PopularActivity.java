package com.framgia.nvmanh.boxmovie.screen.popular;

import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.screen.base.BaseGenresActivity;

public class PopularActivity extends BaseGenresActivity {

    @Override
    public String getType() {
        return Movie.Genres.POPULAR;
    }
}
