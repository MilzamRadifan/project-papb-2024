package com.example.tapetrove;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.bumptech.glide.Glide;

public class profile extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private ImageButton ibAvatar;
    private UserDatabase userDb;
    private UserDao userDao;
    private User user;
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

        userDb = Room.databaseBuilder(this, UserDatabase.class, "user")
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao(); // Menggunakan getDao()

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        loadUserData(username);

        pilihGambar = new pilihGambarProfile(this, ibAvatar);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    EditProfileFragment editProfileFragment = EditProfileFragment.newInstance(
                            user.getUsername(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getTelephone(),
                            user.getAddress()
                    );
                    replaceFragment(editProfileFragment);
                }
            }
        });

        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logout functionality
            }
        });

        content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new historyRentFragment());
            }
        });

        ibAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar.showDialog();
            }
        });
    }

    private void loadUserData(String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                user = userDao.getUserByUsername(username);
                if (user != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvUsername.setText(user.getUsername());
                            tvEmail.setText(user.getEmail());

                            // Mendapatkan URL gambar dari Firebase Database dan menampilkannya
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profileImageUrl");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String imageUrl = dataSnapshot.getValue(String.class);
                                    if (imageUrl != null) {
                                        Glide.with(profile.this).load(imageUrl).into(ibAvatar);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Tangani error
                                }
                            });
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = null;
            if (requestCode == REQUEST_CODE_GALLERY && data != null) {
                selectedImageUri = data.getData();
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                selectedImageUri = pilihGambarProfile.getImageUri();
            }

            if (selectedImageUri != null) {
                pilihGambar.saveImage(selectedImageUri);
            }
        }

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String newUsername = data.getStringExtra("newusername");
            String newEmail = data.getStringExtra("newemail");
            String newNoTelpon = data.getStringExtra("newnotelpon");
            String newAlamat = data.getStringExtra("newalamat");

            tvUsername.setText(newUsername);
            tvEmail.setText(newEmail);

            user.setUsername(newUsername);
            user.setEmail(newEmail);
            user.setTelephone(newNoTelpon);
            user.setAddress(newAlamat);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    userDao.updateUser(user);
                }
            }).start();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layouta, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}