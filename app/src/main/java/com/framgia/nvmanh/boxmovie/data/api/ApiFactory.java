package com.framgia.nvmanh.boxmovie.data.api;

import com.framgia.nvmanh.boxmovie.ultis.Contants;

public class ApiFactory {
    public static BoxMovieApi getApi(){
        return ApiConfig.getInstance(Contants.Server.BASE_URL).create(BoxMovieApi.class);
    }
}
