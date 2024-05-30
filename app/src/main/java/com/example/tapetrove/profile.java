package com.example.tapetrove;

import static com.example.tapetrove.pilihGambarProfile.REQUEST_CODE_CAMERA;
import static com.example.tapetrove.pilihGambarProfile.REQUEST_CODE_GALLERY;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private ImageButton ibAvatar;
    private User user;
    private pilihGambarProfile pilihGambar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

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

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        pilihGambar = new pilihGambarProfile(this, ibAvatar);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            loadUserData(currentUser.getUid());
        }

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    EditProfileFragment editProfileFragment = EditProfileFragment.newInstance(
                            user.getUsername(),
                            user.getEmail(),
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
                mAuth.signOut();
                Intent intent = new Intent(profile.this, SignInActivity.class);
                startActivity(intent);
                finish();
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

    private void loadUserData(String uid) {
        mDatabase.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    tvUsername.setText(user.getUsername());
                    tvEmail.setText(user.getEmail());

                    String imageUrl = user.getProfileImageUrl();
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(profile.this).load(imageUrl).into(ibAvatar);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
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

            mDatabase.child(mAuth.getCurrentUser().getUid()).setValue(user);
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