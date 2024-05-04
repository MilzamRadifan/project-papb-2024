package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class editProfile extends AppCompatActivity {

    private EditText etNama, etEmail, etPassword, etNomorHP, etAlamat;
    private Button btSimpan, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNomorHP = findViewById(R.id.etnomorhp);
        etAlamat = findViewById(R.id.etAlamat);
        btSimpan = findViewById(R.id.btSimpan);
        btCancel = findViewById(R.id.btCancel);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();

                // Ubah teks pada TextView tvNama dan tvEmail di laman profil
                TextView tvNama = findViewById(R.id.tvNama);
                TextView tvEmail = findViewById(R.id.tvEmail);
                tvNama.setText(nama);
                tvEmail.setText(email);
                Toast.makeText(editProfile.this, "Perubahan disimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}