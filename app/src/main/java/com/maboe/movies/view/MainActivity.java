package com.maboe.movies.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.maboe.movies.R;
import com.maboe.movies.adapter.MovieAdapter;
import com.maboe.movies.databinding.ActivityMainBinding;
import com.maboe.movies.model.Movie;
import com.maboe.movies.utils.GridItemDecoration;
import com.maboe.movies.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private PagedList<Movie> movies;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Popular Movies");


        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void getPopularMovies() {

//        mainActivityViewModel.getAllMoviesLiveData().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesLiveData) {
//                movies = (ArrayList<Movie>) moviesLiveData;
//                showOnRecyclerView();
//            }
//        });

        mainActivityViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@NonNull PagedList<Movie> moviesLiveData) {
                movies = moviesLiveData;
                showOnRecyclerView();

            }
        });

    }

    private void showOnRecyclerView() {

        MovieAdapter movieAdapter = new MovieAdapter(this);

        RecyclerView recyclerViewMovies = activityMainBinding.moviesRecyclerview;

        movieAdapter.submitList(movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setAdapter(movieAdapter);
        recyclerViewMovies.addItemDecoration(new GridItemDecoration(this));
        movieAdapter.notifyDataSetChanged();
    }
}
