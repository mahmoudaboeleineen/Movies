package com.maboe.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.maboe.movies.R;
import com.maboe.movies.databinding.ItemTrailerBinding;
import com.maboe.movies.model.MovieTrailer;


import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private Context context;
    private List<MovieTrailer> movieTrailersList;

    public TrailerAdapter(Context context, List<MovieTrailer> movieTrailersList) {
        this.context = context;
        this.movieTrailersList = movieTrailersList;
    }

    @NonNull
    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrailerBinding itemTrailerBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_trailer,
                        parent, false);

        return new TrailerViewHolder(itemTrailerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        MovieTrailer movieTrailer = movieTrailersList.get(position);
        holder.trailerName.setText(movieTrailer.getName());
        Log.d("check00", movieTrailer.getName());

        if (movieTrailer.getSite().equalsIgnoreCase("youtube")) {
            Uri uri = Uri.parse(context.getResources().getString(R.string.YOUTUBE_BASE_IMAGE_URL) + movieTrailer.getKey() + context.getResources().getString(R.string.YOUTUBE_IMAGE_EXTENSION));
            Glide.with(context)
                    .load(uri)
                    .placeholder((R.drawable.loading))
                    .error(R.drawable.loading)
                    .into(holder.trailerImage);
        }

    }

    @Override
    public int getItemCount() {
        return movieTrailersList.size();
    }

    public ArrayList<MovieTrailer> getTrailerList() {
        return (ArrayList<MovieTrailer>) movieTrailersList;
    }

    public void setMovieTrailerList(List<MovieTrailer> movieTrailerList) {
        this.movieTrailersList = movieTrailerList;
        notifyDataSetChanged();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ItemTrailerBinding itemTrailerBinding;

        ImageView trailerImage;
        TextView trailerName;
        ImageView TrailerPlayButton;

        public TrailerViewHolder(@NonNull ItemTrailerBinding itemTrailerBinding) {
            super(itemTrailerBinding.getRoot());

            trailerImage = itemTrailerBinding.getRoot().findViewById(R.id.trailer_image);
            trailerName = itemTrailerBinding.getRoot().findViewById(R.id.trailer_name);
            TrailerPlayButton = itemTrailerBinding.getRoot().findViewById(R.id.play_icon);

            TrailerPlayButton.setOnClickListener(this);
            itemTrailerBinding.getRoot().setOnClickListener(this);
            itemTrailerBinding.getRoot().setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MovieTrailer video = movieTrailersList.get(getAdapterPosition());
            if (video != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(context.getResources().getString(R.string.YOUTUBE_BASE_VIDEO_URL) + video.getKey()));
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Error playing the video", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            MovieTrailer video = movieTrailersList.get(getAdapterPosition());
            if (video != null) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                Uri uri = Uri.parse(context.getResources().getString(R.string.YOUTUBE_BASE_VIDEO_URL) + video.getKey());
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                context.startActivity(Intent.createChooser(sharingIntent, "Share Video url using"));
            }
            return true;
        }
    }
}
