package com.maboe.movies.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.maboe.movies.R;
import com.maboe.movies.databinding.ActivityReviewBinding;
import com.maboe.movies.model.MovieReview;

import java.util.Objects;

public class ReviewActivity extends AppCompatActivity {

    private ActivityReviewBinding activityReviewBinding;
    private MovieReview movieReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        activityReviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Review Details");

        Intent intent = getIntent();
        if (intent.hasExtra("currentReview")) {
            movieReview = getIntent().getParcelableExtra("currentReview");
            activityReviewBinding.setActivityMovieReview(movieReview);
        }
    }
}