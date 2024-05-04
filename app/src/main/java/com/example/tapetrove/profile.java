package com.example.tapetrove;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile extends AppCompatActivity {

    private static final int REQUEST_CODE_EDIT_PROFILE = 1;
    private TextView tvNama;
    private TextView tvEmail;
    private Bitmap ibAvatar;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);
        Button btEdit = findViewById(R.id.btEdit);
        Button btOut = findViewById(R.id.btOut);
        LinearLayout content6 = findViewById(R.id.content6);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, editProfile.class);
                intent.putExtra("nama", tvNama.getText().toString());
                intent.putExtra("email", tvEmail.getText().toString());
                startActivity(intent);
            }
        });

        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        LinearLayout content1 = findViewById(R.id.content1);
//        content1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, akun.class);
//                startActivity(intent);
//            }
//        });

//
//        LinearLayout content2 = findViewById(R.id.content2);
//        content2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, pengaturan.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content3 = findViewById(R.id.content3);
//        content3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, bahasa.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content4 = findViewById(R.id.content4);
//        content4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, notifikasi.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content5 = findViewById(R.id.content5);
//        content5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, bantuan.class);
//                startActivity(intent);
//            }
//        });


        content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, historyRent.class);
                startActivity(intent);
            }
        });

//        LinearLayout content7 = findViewById(R.id.content7);
//        content7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, daftarPinjaman.class);
//                startActivity(intent);
//            }
//        });
    }

}