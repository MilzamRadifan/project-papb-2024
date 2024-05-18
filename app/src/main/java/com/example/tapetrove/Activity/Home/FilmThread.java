package com.example.tapetrove.Activity.Home;

import android.os.Handler;
import android.os.Message;

import com.example.tapetrove.Api.MovieResults;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FilmThread extends Thread {
    private final Handler handler;
    private int page;
    private String category;
    FilmThread(Handler h, int page,String category) {

        this.handler = h;
        this.page=page;
        this.category=category;
    }
    @Override
    public void run() {
        super.run();
        Gson gson = new Gson();
        HttpURLConnection conn = null;
        String link="https://api.themoviedb.org/3/movie/"+category+"?api_key=0fee28739beb2264c434d9af3bfbd40e&language=en-US&page="+page;
        try {
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
                MovieResults movieResults = gson.fromJson(response.toString(), MovieResults.class);
                // Send MovieResults object to UI thread
                Message message = handler.obtainMessage();
                message.obj = movieResults;
                handler.sendMessage(message);
            } else {
                // Handle error response
            }
        } catch (Exception e) {
        }
    }

}
