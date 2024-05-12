package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.databinding.ActivityBerhasilBinding;
import com.example.tapetrove.helper.DateHelper;
import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class BerhasilActivity extends AppCompatActivity {
    TextView textView32,textView33;
    Button button4;
    private Peminjaman moviedb;
    private PeminjamanViewModel peminjamanViewModel;
    private ActivityBerhasilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBerhasilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        peminjamanViewModel = obtainViewModel(BerhasilActivity.this);
        moviedb = new Peminjaman();

        MovieResults.ResultsBean movie = (MovieResults.ResultsBean) getIntent().getSerializableExtra("film");

        Gson gson = new Gson();
        String json = gson.toJson(movie);


        String namaBank=getIntent().getStringExtra("namaBank");
        String statusPembayaran=getIntent().getStringExtra("statusPembayaran");

        moviedb.setId_user(1);
        moviedb.setId_movie(movie.getId());
        moviedb.setMovie(json);
        moviedb.setHarga_sewa(30000);
        moviedb.setMetode_pembayaran(namaBank);
        moviedb.setTanggal_sewa(DateHelper.getCurrentDate());
        moviedb.setTanggal_kembali("");
        moviedb.setTenggat_kembali(DateHelper.getReturnDue());
        moviedb.setStatus_peminjaman("Rental");

        peminjamanViewModel.insert(moviedb);

        textView32=findViewById(R.id.textView32);
        textView33=findViewById(R.id.textView33);
        button4=findViewById(R.id.button4);


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


//        textView33.setText(movie.getTitle());
//        textView32.setText(namaBank);
//        button4.setText(statusPembayaran);

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String statusPembayaran = getIntent().getStringExtra("status_pembayaran");
//                statusPembayaran="berhasil";
//                // Kode untuk berpindah ke activity pembayaran
//                Intent intent = new Intent(BerhasilActivity.this, PeminjamanActivity.class); // Gantilah PembayaranActivity.class dengan nama activity pembayaran Anda
//                intent.putExtra("status_pembayaran", statusPembayaran);
//                startActivity(intent);
                Intent intent = new Intent(BerhasilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    @NonNull
    private static PeminjamanViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(PeminjamanViewModel.class);
    }
}