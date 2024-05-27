package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.tapetrove.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    UserDatabase userDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userDb = Room.databaseBuilder(this,UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        binding.btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailOrUsername = binding.etUsername.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                // Cek apakah input email atau username kosong
                if (emailOrUsername.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please enter email or username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lakukan proses login berdasarkan email atau username
                User user = userDao.signInWithEmail(emailOrUsername, password);
                if (user == null) {
                    // Jika tidak ditemukan user dengan email, coba cari berdasarkan username
                    user = userDao.signInWithUsername(emailOrUsername, password);
                }

                if (user != null) {
                    // Jika user ditemukan, tampilkan pesan berhasil login
                    Toast.makeText(SignInActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, profile.class);
                    intent.putExtra("username", user.getUsername());
                    intent.putExtra("email", user.getEmail());
                    startActivity(intent);
                } else {
                    // Jika user tidak ditemukan, tampilkan pesan gagal login
                    Toast.makeText(SignInActivity.this, "Invalid email/username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.tvmasuksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
}