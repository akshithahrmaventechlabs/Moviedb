package com.newproject.tmdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // Duration in milliseconds
    private TextView textViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewMovie = findViewById(R.id.textViewMovie);

        // Apply fade-in animation
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1500);
        textViewMovie.startAnimation(fadeIn);

        // Use Handler to delay and navigate to the next page
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoginActivity.this, MovieListActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}