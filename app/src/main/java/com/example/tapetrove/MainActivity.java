package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapetrove.profile.AppDatabase;
import com.example.tapetrove.profile.Profile;
import com.example.tapetrove.profile.User;
import com.example.tapetrove.profile.UserDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inisialisasi database
        appDatabase = AppDatabase.getInstance(this);

        addUserToDatabase();


// bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Handle Home item click
                return true;
            } else if (item.getItemId() == R.id.bottom_search) {
                // Handle Search item click
                startActivity(new Intent(getApplicationContext(), Search.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                // Handle Profile item click
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            }
            return false;
        });

    }

    private void addUserToDatabase() {
        UserDao userDao = appDatabase.userDao();

        // Mengambil nama pengguna dan email yang baru
        String newUsername = "Nama Pengguna Baru";

        // Melakukan pengecekan apakah pengguna dengan nama atau email yang sama sudah ada dalam database
        User existingUserByUsername = userDao.getUserByName(newUsername);

        // Jika tidak ada pengguna dengan nama atau email yang sama, tambahkan pengguna baru ke database
        if (existingUserByUsername == null ) {
            // Buat objek User baru
            User newUser = new User(newUsername, "example@gmail.com", "password", "123456789", "Alamat Pengguna Baru");

            // Insert pengguna baru ke database
            userDao.insertUser(newUser);
        }
    }
}