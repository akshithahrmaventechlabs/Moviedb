package com.newproject.tmdb.utils;

import com.newproject.tmdb.models.MovieModel;
import com.newproject.tmdb.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

//Search for movies
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

//    making search with id
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key")String api_key
            );

    //Get popular movie
    //https://api.themoviebd.org/3/movie/popular?api_key=52a18783ed514602a5f15a0177e61&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse>getPopular(
            @Query("api_key")String key,
            @Query("page")int page
    );


    Call<MovieSearchResponse> searchMovie(String apiKey, String action, String number);
}
