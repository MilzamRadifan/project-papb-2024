package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Mendapatkan referensi ke tombol
        Button button = findViewById(R.id.button);

        // Menambahkan pendengar klik ke tombol
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk memulai RatingActivity
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivity(intent); // Memulai aktivitas RatingActivity
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
