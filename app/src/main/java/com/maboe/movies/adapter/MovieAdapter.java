package com.maboe.movies.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.maboe.movies.R;
import com.maboe.movies.databinding.MovieListItemBinding;
import com.maboe.movies.model.Movie;
import com.maboe.movies.view.MovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context context;
//    private ArrayList<Movie> moviesArrayList;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
//        this.moviesArrayList = moviesArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item,
                        parent, false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);

//        String moviePosterPath = "https://image.tmdb.org/t/p/w500/"
//                + movie.getPosterPath();
//        movie.setPosterPath(moviePosterPath);

//        Glide.with(context)
//                .load(moviePosterPath)
//                .placeholder(R.drawable.loading)
//                .into(holder.moviePoster);

        holder.movieListItemBinding.setMovie(movie);
    }

//    @Override
//    public int getItemCount() {
//        return moviesArrayList.size();
//    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());

            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie selectedMovie = getItem(position);
                    Toast.makeText(context, "" + selectedMovie.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", selectedMovie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
