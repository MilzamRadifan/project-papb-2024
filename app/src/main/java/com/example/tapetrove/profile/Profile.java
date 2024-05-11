package com.example.tapetrove.profile;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tapetrove.R;

import java.util.List;

public class Profile extends AppCompatActivity {
  private TextView tvNama;
  private TextView tvEmail;
  private AppDatabase appDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_profile);

    tvNama = findViewById(R.id.tvNama);
    tvEmail = findViewById(R.id.tvEmail);
    Button btEdit = findViewById(R.id.btEdit);
    Button btDelAcc = findViewById(R.id.btDelAcc);

    appDatabase = AppDatabase.getInstance(this);

    displayUserData();
    
    btDelAcc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        deleteUserAndNavigate();
      }
    });

  }

  private void displayUserData() {
    User user = appDatabase.userDao().getUserByName("Nama Pengguna Baru");

    if (user != null) {

      findViewById(R.id.profileLayout).setVisibility(View.VISIBLE);
      findViewById(R.id.noAccLayout).setVisibility(View.INVISIBLE);

      tvNama.setText(user.getUsername());
      tvEmail.setText(user.getEmail());
    } else {
      findViewById(R.id.profileLayout).setVisibility(View.INVISIBLE);
      findViewById(R.id.noAccLayout).setVisibility(View.VISIBLE);
      findViewById(R.id.btDelAcc).setVisibility(View.INVISIBLE);
    }
  }

  private void deleteUserAndNavigate() {
    AppDatabase db = AppDatabase.getInstance(getApplicationContext());

    UserDao userDao = db.userDao();

    User userToDelete = userDao.getUserByName("Nama Pengguna Baru");
    if (userToDelete != null) {
      userDao.deleteUser(userToDelete);

      Intent intent = new Intent(Profile.this, Profile.class);
      startActivity(intent);
    }
  }


//  private void deleteAllUser() {
//    // Mendapatkan instance dari database
//    AppDatabase db = AppDatabase.getInstance(getApplicationContext());
//
//    // Mendapatkan instance dari UserDao
//    UserDao userDao = db.userDao();
//
//    // Panggil metode deleteAllUsers() dari UserDao untuk menghapus semua user
//    userDao.deleteAllUsers();
//
//    Intent intent = new Intent(Profile.this, Profile.class);
//    startActivity(intent);
//  }

//  private void addUserToDatabase() {
//    UserDao userDao = appDatabase.userDao();
//
//    // Mengambil nama pengguna dan email yang baru
//    String newUsername = "Nama Pengguna Baru";
//
//    // Melakukan pengecekan apakah pengguna dengan nama atau email yang sama sudah ada dalam database
//    User existingUserByUsername = userDao.getUserByName(newUsername);
//
//    // Jika tidak ada pengguna dengan nama atau email yang sama, tambahkan pengguna baru ke database
//    if (existingUserByUsername == null ) {
//      // Buat objek User baru
//      User newUser = new User(newUsername, "example@gmail.com", "password", "123456789", "Alamat Pengguna Baru");
//
//      // Insert pengguna baru ke database
//      userDao.insertUser(newUser);
//    }
//  }
}

//    btEdit.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent intent = new Intent(Profile.this, editProfile.class);
//        intent.putExtra("nama", tvNama.getText().toString());
//        intent.putExtra("email", tvEmail.getText().toString());
//        startActivity(intent);
//      }
//    });

//    btOut.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent intent = new Intent(Profile.this, MainActivity.class);
//        startActivity(intent);
//      }
//    });

//        LinearLayout content1 = findViewById(R.id.content1);
//        content1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, akun.class);
//                startActivity(intent);
//            }
//        });

//
//        LinearLayout content2 = findViewById(R.id.content2);
//        content2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, pengaturan.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content3 = findViewById(R.id.content3);
//        content3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, bahasa.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content4 = findViewById(R.id.content4);
//        content4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, notifikasi.class);
//                startActivity(intent);
//            }
//        });

//        LinearLayout content5 = findViewById(R.id.content5);
//        content5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, bantuan.class);
//                startActivity(intent);
//            }
//        });

//    content6.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent intent = new Intent(Profile.this, historyRent.class);
//        startActivity(intent);
//      }
//    });

//        LinearLayout content7 = findViewById(R.id.content7);
//        content7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(profile.this, daftarPinjaman.class);
//                startActivity(intent);
//            }
//        });
