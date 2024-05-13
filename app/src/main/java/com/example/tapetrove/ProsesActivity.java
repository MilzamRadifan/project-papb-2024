package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class ProsesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proses);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

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

        String statusPembayaran = "proses";
        MovieResults.ResultsBean movie = (MovieResults.ResultsBean) getIntent().getSerializableExtra("film");
        String namaBank=getIntent().getStringExtra("namaBank");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Memulai aktivitas BerhasilActivity setelah 5 detik
                Intent intent = new Intent(ProsesActivity.this, BerhasilActivity.class);
                intent.putExtra("statusPembayaran", statusPembayaran);
                intent.putExtra("film",(Serializable) movie);
                intent.putExtra("namaBank", namaBank);
                startActivity(intent);
                finish(); // Mengakhiri ProsesActivity setelah memulai BerhasilActivity
            }
        }, 5000); // 5000 milidetik = 5 detik
    }
}