package com.example.tapetrove;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<MovieResults> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    //https://api.themoviedb.org/3/movie/popular?api_key=0fee28739beb2264c434d9af3bfbd40e&append_to_response=videos,images
}
