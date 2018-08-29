package com.framgia.nvmanh.boxmovie.data.api;

import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoxMovieApi {
    @GET("movie/{type}")
    Observable<MovieResutls> getMovies(
            @Path("type") String type,
            @Query("api_key") String apiKey,
            @Query("page") int page);
}
