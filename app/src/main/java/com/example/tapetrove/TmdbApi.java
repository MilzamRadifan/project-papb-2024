package com.example.tapetrove;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class TmdbApi {

    private static final String BASE_URL = "https://api.themoviedb.org/3/"; // Ubah path sesuai dengan versi API
    private static final String API_KEY = "2abd706b1888d8efd777765ab9edc058";

    private static TmdbApi instance;
    private ApiService apiService;

    private TmdbApi() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer " + API_KEY);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static TmdbApi getInstance() {
        if (instance == null) {
            instance = new TmdbApi();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public interface ApiService {
        @GET("movie/{category}")
        Call<FilmApi> getMovies(
                @Path("category") String category,
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("page") int page
        );
    }
}