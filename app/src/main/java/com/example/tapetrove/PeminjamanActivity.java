package com.example.tapetrove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PeminjamanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

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

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.m3gan_official_trailer);
        videoView.start();

        BottomNavigationView peminjamanMenu = findViewById(R.id.peminjaman_menu);
        peminjamanMenu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_wishlist) {
                startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_bagikan) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo sewa film M3GAN, hanya Rp.30.000 di aplikasi TaveTrove");
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
                String judul = "M3GAN";
                intent.putExtra("judul_data", judul);
                startActivity(intent);
            }
        });

        if (getIntent() != null) {
            String statusPembayaran = getIntent().getStringExtra("status_pembayaran");
            if (statusPembayaran != null&&statusPembayaran.equals("berhasil")){
                btnSewa.setEnabled(false);
                btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
                btnSewa.setTextColor(Color.parseColor("#808080"));
                btnSewa.setText("Sudah Disewa");
            }else{

            }
        }
    }
}