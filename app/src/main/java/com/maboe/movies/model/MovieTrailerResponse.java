package com.maboe.movies.model;


import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieTrailerResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<MovieTrailer> movieTrailerList = null;
    public final static Parcelable.Creator<MovieTrailerResponse> CREATOR = new Creator<MovieTrailerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieTrailerResponse createFromParcel(Parcel in) {
            return new MovieTrailerResponse(in);
        }

        public MovieTrailerResponse[] newArray(int size) {
            return (new MovieTrailerResponse[size]);
        }

    };

    protected MovieTrailerResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movieTrailerList, (com.maboe.movies.model.MovieTrailer.class.getClassLoader()));
    }

    public MovieTrailerResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieTrailer> getMovieTrailerList() {
        return movieTrailerList;
    }

    public void setMovieTrailerList(List<MovieTrailer> movieTrailerList) {
        this.movieTrailerList = movieTrailerList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(movieTrailerList);
    }

    public int describeContents() {
        return 0;
    }

}
