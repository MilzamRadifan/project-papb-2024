package com.example.tapetrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanActivity extends AppCompatActivity {
  private TextView tvTitle;
  private TextView tvGenre;
  private TextView tvScore;
  private TextView tvRating;
  private TextView tvYear;
  private TextView tvDuration;
  private TextView tvSynopsis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_peminjaman);

    tvTitle = findViewById(R.id.tvTitle);
    tvGenre = findViewById(R.id.tvGenre);
    tvScore =findViewById(R.id.tvScore);
    tvRating= findViewById(R.id.tvRating);
    tvYear= findViewById(R.id.tvYear);
    tvDuration= findViewById(R.id.tvDuration);
    tvSynopsis= findViewById(R.id.tvSynopsis);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    bottomNavigationView.setSelectedItemId(R.id.home);
    bottomNavigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home) {
        return true;
      } else if (item.getItemId() == R.id.search) {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.profile) {
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
        return true;
      }
      return false;
    });

    Intent intent = getIntent();
    MovieResults.ResultsBean movie = (MovieResults.ResultsBean) intent.getSerializableExtra("film");

    String title = movie.getTitle();
    String voteAverage = "★ "+ String.format("%.1f", movie.getVote_average());
    String releaseDate = movie.getRelease_date();
    String synopsis =movie.getOverview();


    tvTitle.setText(title);
    tvScore.setText(voteAverage);
    tvYear.setText(releaseDate);
    tvSynopsis.setText(synopsis);

    List<Integer> genre_ids = movie.getGenre_ids();
    List<String> movieGenres = new ArrayList<>();

//        tvGenre.setText(genre_ids.get(0).toString());

    Handler hTrailer = new Handler (Looper.getMainLooper()){
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Trailer trailer = (Trailer) msg.obj;
        List<Trailer.ResultsBean> results = trailer.getResults();
        String linkTrailer="";
        for (int i = 0; i < results.size(); i++) {
          if (results.get(i).getType().equals("Trailer")){
            linkTrailer=results.get(i).getKey();
            break;
          }
        }
        WebView webView =findViewById(R.id.wvTrailer);

        String video="<html><body style=\"padding: 0; margin: 0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+linkTrailer+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>";
        //String video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lV1OOlGwExM\"  allowfullscreen></iframe>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new CustomWebChromeClient(PeminjamanActivity.this));
        webView.loadData(video,"text/html","utf-8");
        bottomNavigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
          @Override
          public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            // Periksa apakah perubahan layout disebabkan oleh perubahan orientasi layar
            if (bottom < oldBottom) {
              // Orientasi layar telah berubah menjadi potret, sembunyikan Bottom Navigation Bar
              bottomNavigationView.setVisibility(View.GONE);
            } else if (bottom > oldBottom) {
              // Orientasi layar telah berubah menjadi landscape, tampilkan Bottom Navigation Bar kembali
              bottomNavigationView.setVisibility(View.VISIBLE);
            }
          }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
          @Override
          public void onSystemUiVisibilityChange(int visibility) {
            // Periksa apakah perubahan visibilitas disebabkan oleh perubahan ke mode potret
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
              // Tampilkan kembali Bottom Navigation Bar saat kembali ke mode potret
              bottomNavigationView.setVisibility(View.VISIBLE);
            }
          }
        });

//        bottomNavigationView.setVisibility(View.INVISIBLE);
      }
    };
    Thread tTrailer =new TrailerThread(hTrailer,Integer.toString(movie.getId()));
    tTrailer.start();


    Handler hGenre = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Genre genre = (Genre) msg.obj;
        List<Genre.GenresBean> results = genre.getGenres();

        for (Integer genreId : genre_ids) {
          for (Genre.GenresBean genresBean : results) {
            if (genresBean.getId() == genreId) {
              movieGenres.add(genresBean.getName());
            }
          }
        }

        String setGenre;
        setGenre = movieGenres.get(0);
        for (int i = 1; i < movieGenres.size(); i++) {
          setGenre = setGenre + " | " + movieGenres.get(i);
        }
        tvGenre.setText(setGenre);
      }
    };
    Thread tGenre = new GenreThread(hGenre);
    tGenre.start();




//    VideoView videoView = (VideoView) findViewById(R.id.videoView);
//    videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.m3gan_official_trailer);
//    videoView.start();

    BottomNavigationView peminjamanMenu = findViewById(R.id.peminjaman_menu);
    peminjamanMenu.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.bottom_wishlist) {
        startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.bottom_bagikan) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo sewa film "+title+", hanya Rp.30.000 di aplikasi TaveTrove");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        return true;
      } else if (item.getItemId() == R.id.bottom_rating) {
        startActivity(new Intent(getApplicationContext(), RatingActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.bottom_komentar) {
        startActivity(new Intent(getApplicationContext(), KomentarActivity.class));
        finish();
        return true;
      }
      return false;
    });

    Button btnSewa = findViewById(R.id.buttonSewa);
    btnSewa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(PeminjamanActivity.this, PembayaranActivity.class);
//        String judul = "M3GAN";
//        intent.putExtra("judul_data", judul);
        intent.putExtra("film", (Serializable) movie);

        // Mulai aktivitas pembayaran dengan Intent yang telah dibuat

        startActivity(intent);
      }
    });

    if (getIntent().getStringExtra("status_pembayaran") != null) {
      String statusPembayaran = getIntent().getStringExtra("status_pembayaran");
      if (statusPembayaran != null && statusPembayaran.equals("berhasil")) {
        btnSewa.setEnabled(false);
        btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnSewa.setTextColor(Color.parseColor("#808080"));
        btnSewa.setText("Sudah Disewa");
      } else {
      }
    }
  }
}