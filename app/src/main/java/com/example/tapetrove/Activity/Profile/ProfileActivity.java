package com.example.tapetrove.Activity.Profile;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapetrove.Activity.Home.HomeFragment;
import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.Activity.Home.PeminjamanFragment;
import com.example.tapetrove.Activity.Search.SearchActivity;
import com.example.tapetrove.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new ProfileFragment());

        if (getIntent() != null && "wishlist".equals(getIntent().getStringExtra("openFragment"))) {
            replaceFragment(new WishlistFragment());
        } else {
            replaceFragment(new ProfileFragment());
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
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
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Fragment currentFragment =
                getSupportFragmentManager()
                        .findFragmentById(R.id.frame_layout);
        // Check if orientation is landscape and the current fragment is the booking fragment
        if (newConfig.orientation
                == Configuration.ORIENTATION_LANDSCAPE
                && currentFragment instanceof PeminjamanFragment) {
            // Hide Bottom Navigation Bar
            findViewById(R.id.bottomNavigation)
                    .setVisibility(View.GONE);
        } else {
            // Show Bottom Navigation Bar
            findViewById(R.id.bottomNavigation)
                    .setVisibility(View.VISIBLE);
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragmentWithBundle(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}