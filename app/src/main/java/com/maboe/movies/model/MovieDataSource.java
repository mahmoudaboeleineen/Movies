package com.maboe.movies.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.maboe.movies.R;
import com.maboe.movies.rest.MovieDataService;
import com.maboe.movies.rest.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieDataService movieDataService;
    private Application application;

    public MovieDataSource(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        movieDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = movieDataService.getPopularMoviesWithPaging
                (application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> moviesList = new ArrayList<>();

                if (movieResponse != null && movieResponse.getMovies() != null) {
                    moviesList = (ArrayList<Movie>) movieResponse.getMovies();

                    callback.onResult(moviesList, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        movieDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = movieDataService.getPopularMoviesWithPaging
                (application.getApplicationContext().getString(R.string.api_key), params.key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> moviesList = new ArrayList<>();

                if (movieResponse != null && movieResponse.getMovies() != null) {
                    moviesList = (ArrayList<Movie>) movieResponse.getMovies();

                    callback.onResult(moviesList, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
