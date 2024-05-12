package com.example.tapetrove.search;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
  @GET("/3/discover/movie")
  Call<ResponseBody> sortMovies (
//          @Path("category") String category,
          @Query("api_key") String apiKey,
          @Query("language") String language,
          @Query("page") int page,
          @Query("sort_by") String sort
  );

  @GET("/3/search/movie")
  Call<ResponseBody> searchMovie (
          @Query("api_key") String apiKey,
          @Query("query") String identifier,
          @Query("language") String language,
          @Query("page") int page

  );
}