package com.example.tapetrove;

import android.content.Intent;
import android.os.AsyncTask;
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
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao(); // Menggunakan getDao()

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
                }

                if (password.equals(confirmPassword)) {
                    new CheckEmailTask(userDao, email, new OnEmailCheckListener() {
                        @Override
                        public void onEmailChecked(boolean exists) {
                            if (!exists) {
                                User newUser = new User(0, username, email, password, notelpon, address);
                                new InsertUserTask(userDao).execute(newUser);
                                Toast.makeText(SignUpActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "User telah Terdaftar, LOGIN", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).execute();
                } else {
                    Toast.makeText(SignUpActivity.this, "Password Berbeda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private interface OnEmailCheckListener {
        void onEmailChecked(boolean exists);
    }

    private static class CheckEmailTask extends AsyncTask<Void, Void, Boolean> {
        private UserDao userDao;
        private String email;
        private OnEmailCheckListener listener;

        CheckEmailTask(UserDao userDao, String email, OnEmailCheckListener listener) {
            this.userDao = userDao;
            this.email = email;
            this.listener = listener;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return userDao.checkEmail(email);
        }

        @Override
        protected void onPostExecute(Boolean exists) {
            listener.onEmailChecked(exists);
        }
    }

    private static class InsertUserTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertUserTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }
}