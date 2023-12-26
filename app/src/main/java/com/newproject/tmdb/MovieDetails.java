package com.newproject.tmdb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.newproject.tmdb.models.MovieModel;

public class MovieDetails extends AppCompatActivity {
    private ImageView imageViewDetails;
    private TextView movieTitleTextView, movieDurationTextView, movieLanguageTextView,
            movieOverviewTextView, movieReleaseDateTextView, movieVoteAverageTextView,movieRatingTextView;

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Get references to views
        imageViewDetails = findViewById(R.id.imageView_details);
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        ratingBar = findViewById(R.id.rating_bar);
        movieDurationTextView = findViewById(R.id.movieDurationTextView);
        movieLanguageTextView = findViewById(R.id.movieLanguageTextView);
//        movieRatingTextView = findViewById(R.id.movieRatingTextView);
        movieOverviewTextView = findViewById(R.id.movieOverviewTextView);
        movieReleaseDateTextView = findViewById(R.id.movieReleaseDateTextView);
//        movieVoteAverageTextView = findViewById(R.id.movieVoteAverageTextView);


        // Get data from Intent
        getDataFromIntent();
    }




    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");




            // Set values to views
            movieTitleTextView.setText("TITLE:"+" "+ movieModel.getTitle());
            movieDurationTextView.setText("DURATION:"+" " +String.valueOf(movieModel.getRuntime())+""+"-MINUTES");
            movieLanguageTextView.setText("LANGUAGE:"+" " +movieModel.getOriginal_language());
            movieOverviewTextView.setText("OVERVIEW:"+" " +movieModel.getMovie_overview());
            movieReleaseDateTextView.setText("RELEASE_DATE:"+" " +movieModel.getRelease_date());
            ratingBar.setRating(movieModel.getVote_average() / 2);

            // Load image using Glide
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path())
                    .into(imageViewDetails);
        }
    }


}



