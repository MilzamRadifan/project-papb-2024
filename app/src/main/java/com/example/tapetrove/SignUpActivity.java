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

                            // Kirim data pengguna ke MainActivity menggunakan Bundle
                            Bundle userData = new Bundle();
                            userData.putString("username", username);
                            userData.putString("email", email);
                            userData.putString("notelpon", notelpon);
                            userData.putString("address", address);

                            Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                            mainIntent.putExtra("userData", userData);
                            startActivity(mainIntent);
                            Toast.makeText(SignUpActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                            finish();
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
