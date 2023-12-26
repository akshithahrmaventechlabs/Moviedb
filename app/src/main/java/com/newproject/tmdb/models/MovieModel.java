package com.newproject.tmdb.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    // Model class for movies
    private String title;
    private String poster_path;

    @SerializedName("backdrop_path")
    private String backdropPath;
    private String release_date;

    @SerializedName("id")
    private int movie_id;
    private float vote_average;

    @SerializedName("overview")
    @Expose
    private String movie_overview;
    private int runtime;
    @SerializedName("original_language")
    private String original_language;

    // Constructor
    public MovieModel(String title, String poster_path, String backdropPath, String release_date, int movie_id, float vote_average, String movie_overview,
                      String original_language, int runtime) {
        this.title = title;
        this.backdropPath = backdropPath;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
        this.runtime = runtime;
        this.original_language = original_language;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        backdropPath = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
        runtime = in.readInt();
        original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    // Getters
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdropPath);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeInt(runtime);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
        dest.writeString(original_language);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", vote_average=" + vote_average +
                ", movie_overview='" + movie_overview + '\'' +
                ", runtime=" + runtime +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}