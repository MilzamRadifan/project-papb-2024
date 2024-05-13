package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    bottomNavigationView.setSelectedItemId(R.id.profile);
    bottomNavigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.home) {
        // Handle Home item click
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
        return true;
      } else if (item.getItemId() == R.id.search) {
        // Handle Search item click

        return true;
      } else if (item.getItemId() == R.id.profile) {
        // Handle Profile item click
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
        return true;
      }
      return false;
    });
  }
}