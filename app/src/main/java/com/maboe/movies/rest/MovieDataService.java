package com.maboe.movies.rest;

import com.maboe.movies.model.MovieResponse;
import com.maboe.movies.model.MovieReviewResponse;
import com.maboe.movies.model.MovieTrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataService {


    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailerResponse> getMovieTrailers(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<MovieReviewResponse> getMovieReviews(@Path("id") int movieId, @Query("api_key") String apiKey);
}
