package com.example.tapetrove;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

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
                // Handle Home item click
                return true;
            } else if (item.getItemId() == R.id.search) {
                // Handle Search item click
                //startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                //finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                // Handle Profile item click
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                //finish();
                return true;
            }
            return false;
        });

        VideoView videoView = (VideoView) findViewById(R.id.videoView);  //casting to VideoView is not Strictly required above API level 26
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.m3gan_official_trailer); //set the path of the video that we need to use in our VideoView
        videoView.start();  //start() method of the VideoView class will start the video to play

        BottomNavigationView peminjamanMenu = findViewById(R.id.peminjaman_menu);
        peminjamanMenu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_wishlist) {
                // Handle Home item click
                //startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_bagikan) {
                // Handle Search item click
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo sewa film M3GAN, hanya Rp.30.000 di aplikasi TaveTrove");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            } else if (item.getItemId() == R.id.bottom_rating) {
                // Handle Profile item click
                //startActivity(new Intent(getApplicationContext(), RatingActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_komentar) {
                // Handle Profile item click
                //startActivity(new Intent(getApplicationContext(), KomentarActivity.class));
                finish();
                return true;
            }
            return false;
        });

        Button btnSewa = findViewById(R.id.buttonSewa);
        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kode untuk berpindah ke activity pembayaran
//                Intent intent = new Intent(PeminjamanActivity.this, PembayaranActivity.class); // Gantilah PembayaranActivity.class dengan nama activity pembayaran Anda
//                String judul = "M3GAN";
//                intent.putExtra("judul_data", judul);
//                startActivity(intent);
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