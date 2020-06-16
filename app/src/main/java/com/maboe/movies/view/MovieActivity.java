package com.maboe.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.maboe.movies.R;
import com.maboe.movies.adapter.ReviewAdapter;
import com.maboe.movies.adapter.TrailerAdapter;
import com.maboe.movies.databinding.ActivityMovieBinding;
import com.maboe.movies.databinding.ItemReviewBinding;
import com.maboe.movies.model.Movie;
import com.maboe.movies.model.MovieReview;
import com.maboe.movies.model.MovieReviewResponse;
import com.maboe.movies.model.MovieTrailer;
import com.maboe.movies.model.MovieTrailerResponse;
import com.maboe.movies.rest.MovieDataService;
import com.maboe.movies.rest.RetrofitInstance;
import com.maboe.movies.utils.HorizontalItemDecoration;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    ReviewAdapter reviewAdapter;
    TrailerAdapter trailerAdapter;
    RecyclerView movieTrailersRecyclerView;
    RecyclerView movieReviewRecyclerView;
    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int width = displaymetrics.widthPixels;
            activityMovieBinding.collapsingToolbarMovie.getLayoutParams().height = (int) Math.round(width / 1.5);
        }

        movieReviewRecyclerView = activityMovieBinding.secondaryLayout.movieReview.recyclerviewReviews;
//        movieTrailersRecyclerView = activityMovieBinding.secondaryLayout.movieVideos.trailersList;
        movieTrailersRecyclerView = findViewById(R.id.trailers_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.backgroundColor));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);
        }

//        String movieBackDrop = "http://image.tmdb.org/t/p/w1280"
//                + movie.getBackdropPath();
//        movie.setBackdropPath(movieBackDrop);


//        String backDrop = movie.getBackdropPath();
//        String imagePathUrl = "http://image.tmdb.org/t/p/w1280" + backDrop;
//
//
//        Glide.with(this)
//                .load(imagePathUrl)
//                .placeholder(R.drawable.loading)
//                .into(moviePoster);
//
//        String image = movie.getPosterPath();
//        String backDropPathUrl = "https://image.tmdb.org/t/p/w780/" + image;
//        Glide.with(this)
//                .load(backDropPathUrl)
//                .placeholder(R.drawable.loading)
//                .into(movieBackDrop);

        getSupportActionBar().setTitle(movie.getTitle());

        getMovieReview();
        getMovieTrailers();
        setupTrailerRecycler();
        setupReviewsRecycler();

    }


    public void getMovieReview() {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<MovieReviewResponse> call = movieDataService.getMovieReviews(
                movie.getId(), this.getString(R.string.api_key));
        call.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                MovieReviewResponse movieReviewResponse = response.body();
                if (movieReviewResponse != null && movieReviewResponse.getResults() != null) {
                    reviewAdapter.setMovieReviewList(response.body().getResults());
                    Log.d("Test0000", response.body().getResults().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {

            }
        });
    }


    public void getMovieTrailers() {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<MovieTrailerResponse> call = movieDataService.getMovieTrailers(
                movie.getId(), this.getString(R.string.api_key));
        call.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                MovieTrailerResponse movieTrailerResponse = response.body();
                if (movieTrailerResponse != null && movieTrailerResponse.getMovieTrailerList() != null) {
                    trailerAdapter.setMovieTrailerList(response.body().getMovieTrailerList());
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {

            }
        });
    }

    public void setupTrailerRecycler() {
        trailerAdapter = new TrailerAdapter(this, new ArrayList<MovieTrailer>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        movieTrailersRecyclerView.setAdapter(trailerAdapter);
        movieTrailersRecyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new HorizontalItemDecoration(this);
        movieTrailersRecyclerView.addItemDecoration(itemDecoration);
    }

    public void setupReviewsRecycler() {
        reviewAdapter = new ReviewAdapter(new ArrayList<MovieReview>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        movieReviewRecyclerView.setAdapter(reviewAdapter);
        movieReviewRecyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new HorizontalItemDecoration(this);
        movieReviewRecyclerView.addItemDecoration(itemDecoration);
    }
}
