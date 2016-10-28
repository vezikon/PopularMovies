package com.vezikon.popularmovies.network;


import com.vezikon.popularmovies.data.Movies;
import com.vezikon.popularmovies.data.Reviews;
import com.vezikon.popularmovies.data.Trailers;

import retrofit.Callback;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by vezikon on 1/28/15.
 */
public interface Api {

    @GET("/discover/movie")
    void movies(@Query("sort_by") String sortBy, @Query("api_key") String apiKey,
                Callback<Movies> callback);

    @GET("/movie/{id}/trailers")
    void trailers(@Path("id") String id, @Query("api_key") String apiKey, Callback<Trailers> callback);

    @GET("/movie/{id}/reviews")
    void reviews(@Path("id") String id, @Query("api_key") String apiKey, Callback<Reviews> callback);
}