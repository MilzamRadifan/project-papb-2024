package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tapetrove.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    UserDatabase userDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userDb = Room.databaseBuilder(this, UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String confirmPassword = binding.etPassconfirm.getText().toString();
                String notelpon = binding.etTelephone.getText().toString();
                String address = binding.etAddress.getText().toString();

                if (username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "ISI SEMUA FIELD KOSONG", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (password.equals(confirmPassword)) {
                        boolean checkUserEmail = userDao.checkEmail(email);
                        if (!checkUserEmail) {
                            // Simpan data pengguna ke dalam database Room
                            User newUser = new User(0, username, password, email, notelpon, address);
                            userDao.insertUser(newUser);

                            // Navigasi ke Activity Profile dan kirim data pengguna
                            Intent profileIntent = new Intent(SignUpActivity.this, profile.class);
                            profileIntent.putExtra("username", username);
                            profileIntent.putExtra("email", email);
                            profileIntent.putExtra("notelpon", notelpon);
                            profileIntent.putExtra("address", address);
                            startActivity(profileIntent);
                            Toast.makeText(SignUpActivity.this, "Anda Berhasil SignUp", Toast.LENGTH_SHORT).show();
                            finish(); // Optional: menutup SignUpActivity agar tidak kembali lagi
                        } else {
                            Toast.makeText(SignUpActivity.this, "User telah Terdaftar, LOGIN", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Password Berbeda", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
