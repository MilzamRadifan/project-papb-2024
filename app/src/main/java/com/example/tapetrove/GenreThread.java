package com.example.tapetrove;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenreThread extends Thread{
  private final Handler handler;

  public GenreThread(Handler handler) {
    this.handler = handler;
  }
  @Override
  public void run() {
    super.run();
    Gson gson = new Gson();
    HttpURLConnection conn = null;
    String link="https://api.themoviedb.org/3/genre/movie/list?api_key=0fee28739beb2264c434d9af3bfbd40e&language=en";
    try {
//      URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=0fee28739beb2264c434d9af3bfbd40e&language=en-US&page=7");
      URL url = new URL(link);

      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
          response.append(line);
        }
        br.close();

        // Convert JSON string to MovieResults object using Gson
//        MovieResults movieResults = gson.fromJson(response.toString(), MovieResults.class);
        Genre genre =gson.fromJson(response.toString(), Genre.class);
        // Send MovieResults object to UI thread
        Message message = handler.obtainMessage();
        message.obj = genre;
        handler.sendMessage(message);

//        MovieResults movieResults = gson.fromJson(response.toString(), MovieResults.class);
//        // Send MovieResults object to the UI thread
//        Message message = handler.obtainMessage();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("movie_results", movieResults);
//        message.setData(bundle);
//        handler.sendMessage(message);
      } else {
        // Handle error response
      }
    } catch (Exception e) {
    }
  }
}
