package com.example.tapetrove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvIdPeminjaman,tvIdMovie,tvTanggalPeminjaman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvIdPeminjaman=findViewById(R.id.tvIdPeminjaman);
        tvIdMovie=findViewById(R.id.tvIdMovie);
        tvTanggalPeminjaman=findViewById(R.id.tvTanggalPeminjaman);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                // Handle Home item click
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.search) {
                // Handle Search item click
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                // Handle Profile item click
                return true;
            }
            return false;
        });

        PeminjamanViewModel peminjamanViewModel = obtainViewModel(ProfileActivity.this);

        peminjamanViewModel.getAllMovies().observe(this, movies -> {
            if (movies != null) {
//                movies.get(0).getId_peminjaman();
//                movies.get(0).getId_movie();
//                movies.get(0).getTanggal_sewa();
//
//                movies.get(0).getMovie();

                Gson gson = new Gson();
                String jsonFromDatabase = movies.get(movies.size()-1).getMovie();

                MovieResults.ResultsBean objekfilm=gson.fromJson(jsonFromDatabase, MovieResults.ResultsBean.class);

                tvIdPeminjaman.setText(Integer.toString(movies.get(movies.size()-1).getId_peminjaman()));
//                tvIdMovie.setText(Integer.toString(movies.get(1).getId_movie()));
                tvIdMovie.setText(Integer.toString(objekfilm.getId()));
                tvTanggalPeminjaman.setText(objekfilm.getTitle());
            }
        });

    }
    @NonNull
    private static PeminjamanViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory =
            ViewModelFactory.getInstance(activity.getApplication());

        return new ViewModelProvider(activity,
            factory).get(PeminjamanViewModel.class);
    }
}