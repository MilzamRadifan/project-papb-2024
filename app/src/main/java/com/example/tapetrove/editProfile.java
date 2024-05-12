package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class editProfile extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword, etnomorhp, etAlamat;
    private UserDatabase userDb;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etnomorhp = findViewById(R.id.etnomorhp);
        etAlamat = findViewById(R.id.etAlamat);
        Button btSimpan = findViewById(R.id.btSimpan);
        Button btCancel = findViewById(R.id.btCancel);

        // Inisialisasi database dan userDao
        userDb = Room.databaseBuilder(this, UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        // Mendapatkan data pengguna dari Intent
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String noTelpon = getIntent().getStringExtra("noTelpon");
        String alamat = getIntent().getStringExtra("alamat");

        // Set data pengguna ke EditText
        etUsername.setText(username);
        etEmail.setText(email);
        etPassword.setText(password);
        etnomorhp.setText(noTelpon);
        etAlamat.setText(alamat);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etUsername.getText().toString();
                String newEmail = etEmail.getText().toString();
                String newPassword = etPassword.getText().toString();
                String newnoTelpon = etnomorhp.getText().toString();
                String newAlamat = etAlamat.getText().toString();

                // Perbarui data pengguna di database
                User updatedUser = new User(0, newUsername, newEmail, newPassword, newnoTelpon, newAlamat);
                userDao.updateUser(updatedUser);

                // Kembalikan ke Activity Profile
                Intent intent = new Intent();
                intent.putExtra("newusername", newUsername);
                intent.putExtra("newemail", newEmail);
                intent.putExtra("newnotelpon", newnoTelpon);
                intent.putExtra("newalamat", newAlamat);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
