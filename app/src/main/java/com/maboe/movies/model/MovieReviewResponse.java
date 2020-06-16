package com.maboe.movies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class MovieReviewResponse implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private ArrayList<MovieReview> results;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<MovieReview> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieReview> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}


//public class MovieReviewResponse implements Parcelable {
//
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("page")
//    @Expose
//    private Integer page;
//    @SerializedName("Reviews")
//    @Expose
//    private List<MovieReview> movieReviews;
//    @SerializedName("total_pages")
//    @Expose
//    private Integer totalPages;
//    @SerializedName("total_results")
//    @Expose
//    private Integer totalReviews;
//    public final static Parcelable.Creator<MovieReviewResponse> CREATOR =
//            new Creator<MovieReviewResponse>() {
//
//
//        @SuppressWarnings({
//                "unchecked"
//        })
//        public MovieReviewResponse createFromParcel(Parcel in) {
//            return new MovieReviewResponse(in);
//        }
//
//        public MovieReviewResponse[] newArray(int size) {
//            return (new MovieReviewResponse[size]);
//        }
//
//    };
//
//    protected MovieReviewResponse(Parcel in) {
//        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        in.readList(this.movieReviews, (MovieReview.class.getClassLoader()));
//        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.totalReviews = ((Integer) in.readValue((Integer.class.getClassLoader())));
//    }
//
//    public MovieReviewResponse() {
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getPage() {
//        return page;
//    }
//
//    public void setPage(Integer page) {
//        this.page = page;
//    }
//
//    public List<MovieReview> getMovieReviewsList() {
//        return movieReviews;
//    }
//
//    public void setMovieReviews(List<MovieReview> movieReviews) {
//        this.movieReviews = movieReviews;
//    }
//
//    public Integer getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(Integer totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    public Integer getTotalReviews() {
//        return totalReviews;
//    }
//
//    public void setTotalReviews(Integer totalReviews) {
//        this.totalReviews = totalReviews;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(id);
//        dest.writeValue(page);
//        dest.writeList(movieReviews);
//        dest.writeValue(totalPages);
//        dest.writeValue(totalReviews);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}