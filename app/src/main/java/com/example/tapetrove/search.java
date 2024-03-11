package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Handle Home item click
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_search) {
                // Handle Search item click
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                // Handle Profile item click
//                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });

    }
}