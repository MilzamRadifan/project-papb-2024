package com.example.tapetrove;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

public class profile extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private ImageButton ibAvatar;
    private UserDatabase userDb;
    private UserDao userDao;
    private pilihGambarProfile pilihGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        Button btEdit = findViewById(R.id.btEdit);
        Button btOut = findViewById(R.id.btOut);
        ibAvatar = findViewById(R.id.ibAvatar);
        LinearLayout content6 = findViewById(R.id.content6);

        // Inisialisasi database dan userDao
        userDb = Room.databaseBuilder(this, UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        // Mendapatkan data pengguna dari database
        String username = getIntent().getStringExtra("username");
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
        }

        // Inisialisasi objek pilihGambar
        pilihGambar = new pilihGambarProfile(this, ibAvatar);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(profile.this, editProfile.class);
                editProfileIntent.putExtra("username", user.getUsername());
                editProfileIntent.putExtra("email", user.getEmail());
                editProfileIntent.putExtra("notelpon", user.getTelephone());
                editProfileIntent.putExtra("alamat", user.getAddress());
                startActivityForResult(editProfileIntent, 1);
            }
        });

        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, historyRent.class);
                startActivity(intent);
            }
        });

        ibAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar.showDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String newUsername = data.getStringExtra("newusername");
            String newEmail = data.getStringExtra("newemail");
            String newNoTelpon = data.getStringExtra("newnotelpon");
            String newAlamat = data.getStringExtra("newalamat");

            // Update tampilan profil dengan data yang diperbarui
            tvUsername.setText(newUsername);
            tvEmail.setText(newEmail);

            // Update data pengguna di database (jika perlu)
            User updatedUser = new User(0, newUsername, newEmail, "", newNoTelpon, newAlamat);
            userDao.updateUser(updatedUser);
        }
    }
}
