package com.maboe.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.maboe.movies.R;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.maboe.movies.databinding.ItemReviewBinding;
import com.maboe.movies.model.MovieReview;
import com.maboe.movies.view.ReviewActivity;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder> {

    private List<MovieReview> movieReviewsList;
    Context context;

    public ReviewAdapter(List<MovieReview> movieReviewsList, Context context) {
        this.movieReviewsList = movieReviewsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewBinding itemReviewBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_review
                        , parent, false);

        return new ReviewAdapterViewHolder(itemReviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapterViewHolder holder, int position) {
        MovieReview movieReview = movieReviewsList.get(position);
        holder.itemReviewBinding.setMovieReview(movieReview);
    }

    @Override
    public int getItemCount() {
        return movieReviewsList != null ? movieReviewsList.size() : 0;
    }

    public ArrayList<MovieReview> getReviewList() {
        return (ArrayList<MovieReview>) movieReviewsList;
    }

    public void setMovieReviewList(List<MovieReview> movieReviewList) {
        this.movieReviewsList = movieReviewList;
        notifyDataSetChanged();
    }


    public class ReviewAdapterViewHolder extends RecyclerView.ViewHolder {

        private ItemReviewBinding itemReviewBinding;


        public ReviewAdapterViewHolder(@NonNull ItemReviewBinding itemReviewBinding) {
            super(itemReviewBinding.getRoot());
            this.itemReviewBinding = itemReviewBinding;


            itemReviewBinding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    MovieReview selectedMovieReview = movieReviewsList.get(position);
                    Intent intent = new Intent(context, ReviewActivity.class);
                    intent.putExtra("currentReview", selectedMovieReview);
                    context.startActivity(intent);
                }
            });
        }
    }
}
