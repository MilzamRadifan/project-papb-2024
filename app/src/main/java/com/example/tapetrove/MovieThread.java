package com.example.tapetrove;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieThread extends Thread {
    private final Handler handler;

    public MovieThread(Handler handler) {
        this.handler = handler;
    }

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "0fee28739beb2264c434d9af3bfbd40e";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    @Override
    public void run() {
        try {
//            URL url = new URL("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//
//            Gson gson = new Gson();
//            if(conn.getResponseCode()==200) {
//                //baca respon stream dari server
//                //convert ke string
//                InputStream is = conn.getInputStream();
//                StringBuilder sb = new StringBuilder();
//                BufferedReader br = new BufferedReader(
//                        new InputStreamReader(is)
//                );
//                String line = "";
//                while ((line = br.readLine()) != null) {
//                    sb.append(line).append('\n');
//                }
//                br.close();
//
////                String todoJson = gson.toJson(sb);
//                String todoJson = sb.toString();
//                //DataTodo dataTodo = gson.fromJson(todoJson,DataTodo.class);
//
//                //DataTodo[] dataTodos = gson.fromJson(todoJson, DataTodo[].class);
//                MovieResults movieResults = gson.fromJson(todoJson,MovieResults.class);
//                Message message = handler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putString("movieResultsJson",todoJson); // Ganti jsonString dengan data yang diperoleh dari server
//                message.setData(bundle);
//                handler.sendMessage(message);
//            }else{}


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface myInterface = retrofit.create(ApiInterface.class);
            Call<MovieResults> call = myInterface.listOfMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

            call.enqueue(new Callback<MovieResults>() {
                @Override
                public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                    MovieResults results = response.body();

                    // Konversi objek MovieResults menjadi JSON string
                    Gson gson = new Gson();
                    String json = gson.toJson(results);

                    // Kirim JSON string ke MainActivity melalui Handler
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("movieResultsJson", json);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                @Override
                public void onFailure(Call<MovieResults> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {
            // Handle exceptions (if needed)
        }
    }
}