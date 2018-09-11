package com.framgia.nvmanh.boxmovie.data.api;

import com.framgia.nvmanh.boxmovie.data.model.GenresResults;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.MovieResutls;
import com.framgia.nvmanh.boxmovie.data.model.ReviewResults;

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

    @GET("trending/movie/week")
    Observable<MovieResutls> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}?append_to_response=casts%2Cvideos%2Creviews%2Crecommendations")
    Observable<MovieDetail> getMovieDetail(
            @Path("id") int id,
            @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ReviewResults> getReviews(@Path("id") int id,
                                         @Query("api_key") String apiKey,
                                         @Query("page") int page);

    @GET("genre/movie/list")
    Observable<GenresResults> getGenres(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<MovieResutls> getMoviesByGenres(
            @Query("api_key") String apiKey,
            @Query("with_genres") int genresId,
            @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResutls> getMoviesByCast(
            @Query("api_key") String apiKey,
            @Query("with_cast") int castId,
            @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResutls> getMoviesByCompany(
            @Query("api_key") String apiKey,
            @Query("with_companies") int companyId,
            @Query("page") int page);

    @GET("search/movie")
    Observable<MovieResutls> searchMoviesByTitle(
            @Query("api_key") String apiKey,
            @Query("query") String title,
            @Query("page") int page);
}
