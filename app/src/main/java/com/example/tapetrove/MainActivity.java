package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapetrove.Activity.Search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        Bottomnavigationbar logic
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                // Handle Home item click
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.search) {
                // Handle Search item click
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                // Handle Profile item click
//        startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            }
            return false;
        });
    }
}