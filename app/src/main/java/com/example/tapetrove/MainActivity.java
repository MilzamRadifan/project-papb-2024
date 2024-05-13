package com.example.tapetrove;

import static android.text.TextUtils.replace;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapetrove.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Inisialisasi binding
        setContentView(binding.getRoot()); // Gunakan binding.root sebagai content view

        Bundle userData = getIntent().getBundleExtra("userData");
        if (userData != null) {
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(userData);
            replaceFragment(profileFragment);
        }

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView2;
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.search) {
                    replaceFragment(new SearchFragment());
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    replaceFragment(new ProfileFragment());
                    return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}