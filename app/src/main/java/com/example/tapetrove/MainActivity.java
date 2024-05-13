package com.example.tapetrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  private List<Film> dataTopPoster;
  private List<Film> dataPosterNewReleased;
  private List<Film> dataPosterTrending;
  private List<Film> dataPosterUpcoming;
  private List<Film> dataPosterTopRating;

  private RecyclerView rvFilm;
  private RecyclerView rvPosterNewReleased;
  private RecyclerView rvPosterTrending;
  private RecyclerView rvPosterUpcoming;
  private RecyclerView rvPosterTopRating;

  private Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.rvFilm = this.findViewById(R.id.rvFilm);
    this.rvPosterNewReleased = this.findViewById(R.id.rvPosterNewReleased);
    this.rvPosterTrending = this.findViewById(R.id.rvPosterTrending);
    this.rvPosterUpcoming = this.findViewById(R.id.rvPosterUpcoming);
    this.rvPosterTopRating = this.findViewById(R.id.rvPosterTopRating);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    bottomNavigationView.setSelectedItemId(R.id.home);

    bottomNavigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home) {
        // Handle Home item click
        return true;
      } else if (item.getItemId() == R.id.search) {
        // Handle Search item click
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.profile) {
        // Handle Profile item click
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
        return true;
      }
      return false;
    });

    /* Menginstansiasi data untuk ditampilkan ke reyclerview */
//    Film m3gan = Film.createFilmWithDrawable(this, "M3GAN", "Horror / Thriller / Mystery", R.drawable.megan_top, R.drawable.m3gan, false);
//    Film toyStory = Film.createFilmWithDrawable(this, "Toy Story", "Adventure / Animation / Comedy", R.drawable.toystory_top, R.drawable.toystory_poster, false);
//    Film fastX = Film.createFilmWithDrawable(this, "Fast X", "Action / Crime / Adventure", R.drawable.fastx_top, R.drawable.fastx_poster, false);
//    Film mrBeanHoliday = Film.createFilmWithDrawable(this, "Mr.Bean Holiday", "Comedy / Children's Film / Teen", R.drawable.mrbean_top, R.drawable.mrbean_poster, false);
//    Film theNun = Film.createFilmWithDrawable(this, "The Nun", "Horror / Thriller / Mystery", R.drawable.thenun_top, R.drawable.thenun_poster, false);
//    Film titanic = Film.createFilmWithDrawable(this, "Titanic", "Romance / Drama / Documentary", R.drawable.titanic_top, R.drawable.titanic_poster, false);
//    Film ted2 = Film.createFilmWithDrawable(this, "Ted 2", "Comedy", R.drawable.ted2_poster, false);
//    Film sawX = Film.createFilmWithDrawable(this, "SAW X", "Horror / Thriller", R.drawable.sawx_poster, false);
//    Film evilDeadRise = Film.createFilmWithDrawable(this, "Evil Dead Rise", "Horror / Thriller", R.drawable.evildeadrise_poster, false);
//    Film scream = Film.createFilmWithDrawable(this, "Scream", "Horror / Thriller", R.drawable.scream_poster, false);
//    Film texasChainsaw = Film.createFilmWithDrawable(this, "Texas Chainsaw Massacre", "Horror / Thriller", R.drawable.texaschainsaw_poster, false);
//    Film wayOfWater = Film.createFilmWithDrawable(this, "Avatar: The Way of Water", "Adventure / Action", R.drawable.thewayofwater_poster, true);
//    Film endgame = Film.createFilmWithDrawable(this, "Avengers: Endgame", "Super Hero / Action", R.drawable.endgame_poster, true);
//    Film forceAwaken = Film.createFilmWithDrawable(this, "Star Wars: The Force awaken", "Action", R.drawable.theforceawaken_poster, true);
//    Film beautyBeast = Film.createFilmWithDrawable(this, "Beauty & the Beast", "Romance", R.drawable.beautybeast_poster, true);
//    Film rushHour = Film.createFilmWithDrawable(this, " Rush Hour", "Comedy", R.drawable.rushhour_poster, true);
//    Film it = Film.createFilmWithDrawable(this, "IT", "Horror", R.drawable.it_poster, true);

    /* Menampilkan poster film horizontal menggunakan Arraylist */
//    this.dataTopPoster = new ArrayList<Film>();
//    this.dataTopPoster.add(m3gan);
//    this.dataTopPoster.add(toyStory);
//    this.dataTopPoster.add(fastX);
//    this.dataTopPoster.add(mrBeanHoliday);
//    this.dataTopPoster.add(theNun);
//    this.dataTopPoster.add(titanic);
//    this.rvFilm = this.findViewById(R.id.rvFilm);
//    FilmAdapter filmAdapter =
//        new FilmAdapter(MainActivity.this, this.dataTopPoster);
//    RecyclerView.LayoutManager lmTopPoster =
//        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//    SnapHelper snapHelper = new PagerSnapHelper();
//    snapHelper.attachToRecyclerView(rvFilm);
//    this.rvFilm.setLayoutManager(lmTopPoster);
//    this.rvFilm.setAdapter(filmAdapter);

      /* Menampilkan poster film baru dirilis menggunakan Arraylist */
//    this.dataPosterNewReleased = new ArrayList<Film>();
//    this.dataPosterNewReleased.add(ted2);
//    this.dataPosterNewReleased.add(sawX);
//    this.dataPosterNewReleased.add(fastX);
//    this.dataPosterNewReleased.add(m3gan);
//    this.dataPosterNewReleased.add(evilDeadRise);
//    this.dataPosterNewReleased.add(toyStory);
//    this.rvPosterNewReleased = this.findViewById(R.id.rvPosterNewReleased);
//    PosterAdapter posterAdapter =
//        new PosterAdapter(MainActivity.this, this.dataPosterNewReleased);
//    RecyclerView.LayoutManager lmPosterNewReleased =
//        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//    this.rvPosterNewReleased.setLayoutManager(lmPosterNewReleased);
//    this.rvPosterNewReleased.setAdapter(posterAdapter);

      /* Menjalankan thread untuk mengambil data film baru dirilis dari API TMDB menggunakan Retrofit */
      /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
//    handler = new Handler(new Handler.Callback() {
//      @Override
//      public boolean handleMessage(Message msg) {
//        Bundle bundle = msg.getData();
//        String json = bundle.getString("movieResultsJson");
//        Gson gson = new Gson();
//        MovieResults movieResults = gson.fromJson(json, MovieResults.class);
//        List<MovieResults.ResultsBean> results = movieResults.getResults();
//        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
//        RecyclerView.LayoutManager lmPosterNewReleased =
//            new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//
//        rvPosterNewReleased.setLayoutManager(lmPosterNewReleased);
//        rvPosterNewReleased.setAdapter(movieAdapter);
////                MovieResults.ResultsBean firstmovie = movieResults.getResults().get(0);
////                textView.setText(firstmovie.getTitle());
//        // Lakukan sesuatu dengan objek MovieResults di sini
//        return false;
//      }
//    });
//    Thread movieThread = new MovieThread(handler);
//    movieThread.start();



      /* Menampilkan poster film trending menggunakan Arraylist */
//    this.dataPosterTrending = new ArrayList<Film>();
//    this.dataPosterTrending.add(mrBeanHoliday);
//    this.dataPosterTrending.add(titanic);
//    this.dataPosterTrending.add(texasChainsaw);
//    this.dataPosterTrending.add(sawX);
//    this.dataPosterTrending.add(scream);
//    this.dataPosterTrending.add(ted2);
//    this.rvPosterTrending = this.findViewById(R.id.rvPosterTrending);
//    RecyclerView.LayoutManager lmPosterTrending =
//        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//    PosterAdapter posterTrendingAdapter =
//        new PosterAdapter(MainActivity.this, this.dataPosterTrending);
//    this.rvPosterTrending.setLayoutManager(lmPosterTrending);
//    this.rvPosterTrending.setAdapter(posterTrendingAdapter);




      /* Menampilkan poster film upcoming menggunakan Arraylist */
//    this.dataPosterUpcoming = new ArrayList<Film>();
//    this.dataPosterUpcoming.add(it);
//    this.dataPosterUpcoming.add(endgame);
//    this.dataPosterUpcoming.add(wayOfWater);
//    this.dataPosterUpcoming.add(forceAwaken);
//    this.dataPosterUpcoming.add(rushHour);
//    this.dataPosterUpcoming.add(beautyBeast);
//    this.rvPosterUpcoming = this.findViewById(R.id.rvPosterUpcoming);
//    RecyclerView.LayoutManager lmPosterUpcoming =
//        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//    PosterAdapter posterUpcomingAdapter =
//        new PosterAdapter(MainActivity.this, this.dataPosterUpcoming);
//    this.rvPosterUpcoming.setLayoutManager(lmPosterUpcoming);
//    this.rvPosterUpcoming.setAdapter(posterUpcomingAdapter);



    /* Menampilkan poster film toprating menggunakan Arraylist */
//    this.dataPosterTopRating = new ArrayList<Film>();
//    this.dataPosterTopRating.add(m3gan);
//    this.dataPosterTopRating.add(theNun);
//    this.dataPosterTopRating.add(evilDeadRise);
//    this.dataPosterTopRating.add(sawX);
//    this.dataPosterTopRating.add(texasChainsaw);
//    this.dataPosterTopRating.add(scream);
//    this.rvPosterTopRating = this.findViewById(R.id.rvPosterTopRating);
//    RecyclerView.LayoutManager lmPosterHorror =
//        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//    PosterAdapter posterHorrorAdapter =
//        new PosterAdapter(MainActivity.this, this.dataPosterTopRating);
//    this.rvPosterTopRating.setLayoutManager(lmPosterHorror);
//    this.rvPosterTopRating.setAdapter(posterHorrorAdapter);

    /* Menjalankan thread untuk mengambil data sedan tayang dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan poster horizontal dari data yang telah diambil menggunakan RecyclerView */
    Handler hTopPoster = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        FilmAdapter filmAdapter = new FilmAdapter(MainActivity.this, results);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvFilm);
        rvFilm.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvFilm.setAdapter(filmAdapter);
      }
    };
    Thread tTopPoster = new FilmThread(hTopPoster, 3, "now_playing");
    tTopPoster.start();


    /* Menjalankan thread untuk mengambil data film baru dirilis dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hNewReleased = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
        rvPosterNewReleased.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvPosterNewReleased.setAdapter(movieAdapter);
      }
    };
    Thread tNewReleased = new FilmThread(hNewReleased, 1, "now_playing");
    tNewReleased.start();


    /* Menjalankan thread untuk mengambil data film trending dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hTrending = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
        rvPosterTrending.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvPosterTrending.setAdapter(movieAdapter);
      }
    };
    Thread tTrending = new FilmThread(hTrending, 2, "popular");
    tTrending.start();


    /* Menjalankan thread untuk mengambil data film upcoming dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hUpcoming = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
        rvPosterUpcoming.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvPosterUpcoming.setAdapter(movieAdapter);
      }
    };
    Thread tUpcoming = new FilmThread(hUpcoming, 1, "upcoming");
    tUpcoming.start();


    /* Menjalankan thread untuk mengambil data film top rating dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hTopRating = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
        rvPosterTopRating.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvPosterTopRating.setAdapter(movieAdapter);
      }
    };
    Thread tTopRating = new FilmThread(hTopRating, 1, "top_rated");
    tTopRating.start();

  }
}