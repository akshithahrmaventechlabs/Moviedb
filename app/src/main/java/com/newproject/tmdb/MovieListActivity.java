package com.newproject.tmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.newproject.tmdb.adapters.MovieRecyclerView;
import com.newproject.tmdb.adapters.OnMovieListener;
import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.request.Service;
import com.newproject.tmdb.response.MovieSearchResponse;
import com.newproject.tmdb.utils.Credentials;
import com.newproject.tmdb.utils.MovieApi;
import com.newproject.tmdb.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {


    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;

    private SearchView searchView;


    GridView gridView;


    //    ViewModel
    private MovieListViewModel movieListViewModel;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.search_view);




        //searchbar
        SetupSearchView();




        recyclerView = findViewById(R.id.recyclerView);


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();



        searchMovieApi("war", 1);


    }




    //Observing any data change
    private void ObserveAnyChange(){

        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        Log.v("Tag", "Name: " + movieModel.getTitle());
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query, pageNumber);
    }




    private void ConfigureRecyclerView(){
        movieRecyclerViewAdapter =  new MovieRecyclerView(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(movieRecyclerViewAdapter);


        //RecyclerView Pagination loading next page of api response
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    //here we need to display the next search results

                    movieListViewModel.searchNextPage();

                }

            }
        });


    }


    @Override
    public void onMovieClick(int position) {
        Toast.makeText(this, "The Position "  +position, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {


    }
    @Override
    public void onBackPressed() {
        // Override the back button behavior
        Intent intent = new Intent(this, MovieListActivity.class);
        startActivity(intent);
        finish(); // Optional: Call finish to remove this activity from the stack
    }
//    public void onPopularButtonClick(View view) {
//        Intent intent = new Intent(this, PopularActivity.class);
//        startActivity(intent);
//    }

    public void onSearchButtonClick(View view) {
        searchView.requestFocus();
        // Optionally, you can also open the keyboard to allow immediate input
        searchView.setIconified(false);
    }
    private void SetupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform movie search when the user submits the query
                movieListViewModel.searchMovieApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text changes if needed
                return false;
            }
        });

    }


}













//package com.newproject.tmdb;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.appcompat.widget.Toolbar;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.GridView;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.newproject.tmdb.adapters.MovieRecyclerView;
//import com.newproject.tmdb.adapters.OnMovieListener;
//import com.newproject.tmdb.models.MovieModel;
//import com.newproject.tmdb.viewmodels.MovieListViewModel;
//import java.util.List;
//
//
//public class MovieListActivity extends AppCompatActivity implements OnMovieListener {
//
//
//    private RecyclerView recyclerView;
//    private MovieRecyclerView movieRecyclerViewAdapter;
//
//    private SearchView searchView;
//
//
//    GridView gridView;
//
//
//    //    ViewModel
//    private MovieListViewModel movieListViewModel;
//
//    boolean isPopular=true;
//
//    TextView bottomNavigationItemView1;
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        bottomNavigationItemView1 =findViewById(R.id.btn_popular);
//
//
//
//        bottomNavigationItemView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObservePopularMovies();
//            }
//        });
//
//
//
//
//
//        //Toolbar
//        Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        searchView = findViewById(R.id.search_view);
//
//
//
//
//        //searchbar
//        SetupSearchView();
//
//
//
//
//        recyclerView = findViewById(R.id.recyclerView);
//
//
//        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
//
//        ConfigureRecyclerView();
//        ObserveAnyChange();
//        ObservePopularMovies();
//        movieListViewModel.searchMoviePop(1);
//
//
//
//        searchMovieApi("war", 1);
//
//
//    }
//
//    private void ObservePopularMovies() {
//
//        movieListViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
//            @Override
//            public void onChanged(List<MovieModel> movieModels) {
//                // Observing for any data change
//                if (movieModels != null) {
//                    for (MovieModel movieModel : movieModels) {
//                        Log.v("Tag", "Name: " + movieModel.getTitle());
//                        movieRecyclerViewAdapter.setmMovies(movieModels);
//                    }
//                }
//            }
//        });
//
//    }
//
//
//    //Observing any data change
//    private void ObserveAnyChange(){
//
//        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
//            @Override
//            public void onChanged(List<MovieModel> movieModels) {
//                // Observing for any data change
//                if (movieModels != null) {
//                    for (MovieModel movieModel : movieModels) {
//                        Log.v("Tag", "Name: " + movieModel.getTitle());
//                        movieRecyclerViewAdapter.setmMovies(movieModels);
//                    }
//                }
//            }
//        });
//    }
//
//    private void searchMovieApi(String query, int pageNumber){
//        movieListViewModel.searchMovieApi(query, pageNumber);
//    }
//
//
//
//
//    private void ConfigureRecyclerView(){
//        movieRecyclerViewAdapter =  new MovieRecyclerView(this);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setAdapter(movieRecyclerViewAdapter);
//
//
//        //RecyclerView Pagination loading next page of api response
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                if(!recyclerView.canScrollVertically(1)){
//                    //here we need to display the next search results
//
//                    movieListViewModel.searchNextPage();
//
//                }
//
//            }
//        });
//
//
//    }
//
//
//    @Override
//    public void onMovieClick(int position) {
//        Toast.makeText(this, "The Position "  +position, Toast.LENGTH_SHORT).show();
//
//        Intent intent=new Intent(this,MovieDetails.class);
//        intent.putExtra("movie",movieRecyclerViewAdapter.getSelectedMovie(position));
//        startActivity(intent);
//    }
//
//    @Override
//    public void onCategoryClick(String category) {
//
//
//    }
//    @Override
//    public void onBackPressed() {
//        // Override the back button behavior
//        Intent intent = new Intent(this, MovieListActivity.class);
//        startActivity(intent);
//        finish(); // Optional: Call finish to remove this activity from the stack
//    }
////    public void onPopularButtonClick(View view) {
////        Intent intent = new Intent(this, PopularActivity.class);
////        startActivity(intent);
////    }
//
//    public void onSearchButtonClick(View view) {
//        searchView.requestFocus();
//        // Optionally, you can also open the keyboard to allow immediate input
//        searchView.setIconified(false);
//    }
//    private void SetupSearchView() {
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Perform movie search when the user submits the query
//                movieListViewModel.searchMovieApi(query, 1);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle text changes if needed
//                return false;
//            }
//        });
//
//    }
//
//
//}