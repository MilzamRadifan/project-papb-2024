package com.example.tapetrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class PembayaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
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

        NavigationView navigationView = findViewById(R.id.pembayaran_menu);
        // Menangani klik item menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                String namaBank = "";
                // Menutup drawer setelah item menu diklik
                if (item.getItemId() == R.id.nav_qris) {
                    // Pindah ke QrisActivity saat item Qris diklik
                    namaBank = "QRIS";
                    startTransferActivity(namaBank);

                }else if (item.getItemId() == R.id.nav_bni) {
                    // Pindah ke QrisActivity saat item BNI diklik
                    namaBank = "Bank BNI";
                    startTransferActivity(namaBank);

                }else if (item.getItemId() == R.id.nav_mandiri) {
                    // Pindah ke QrisActivity saat item Mandiri diklik
                    namaBank = "Bank Mandiri";
                    startTransferActivity(namaBank);

                }else if (item.getItemId() == R.id.nav_bca) {
                    // Pindah ke QrisActivity saat item BCA diklik
                    namaBank = "Bank BCA";
                    startTransferActivity(namaBank);

                }else if (item.getItemId() == R.id.nav_bri) {
                    // Pindah ke QrisActivity saat item BRI diklik
                    namaBank = "Bank BRI";
                    startTransferActivity(namaBank);

                }

                return true;
            }
            String judulDataPeminjaman = getIntent().getStringExtra("judul_data");

            private void startTransferActivity(String namaBank) {
                // Membuat Intent untuk berpindah ke TransferActivity
                Intent intent = TransferActivity.newIntent(PembayaranActivity.this, namaBank);

                // Menambahkan data ke Intent
                String judulData = judulDataPeminjaman;
                intent.putExtra("judul_data", judulData);

                // Memulai aktivitas TransferActivity dengan membawa data
                startActivity(intent);
            }
        });



    }
}