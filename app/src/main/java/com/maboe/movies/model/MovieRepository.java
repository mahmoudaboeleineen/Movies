package com.maboe.movies.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.maboe.movies.R;
import com.maboe.movies.rest.MovieDataService;
import com.maboe.movies.rest.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    Application application;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();


    public MovieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<MovieResponse> call = movieDataService.getPopularMovies(
                application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null && movieResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

//    public MutableLiveData<List<MovieTrailer>> getTrailerMutableLiveData() {
//        MovieDataService movieDataService = RetrofitInstance.getService();
//        Call<MovieTrailerResponse> call = movieDataService.getMovieTrailers(
//                movie.getId(), application.getApplicationContext().getString(R.string.api_key));
//        call.enqueue(new Callback<MovieTrailerResponse>() {
//            @Override
//            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
//                MovieTrailerResponse movieTrailerResponse = response.body();
//                if (movieTrailerResponse != null && movieTrailerResponse.getMovieTrailerList() != null) {
//                    movieTrailers = (ArrayList<MovieTrailer>) movieTrailerResponse.getMovieTrailerList();
//                    trailerMutableLiveData.setValue(movieTrailers);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
//
//            }
//        });
//        return trailerMutableLiveData;
//    }


}
