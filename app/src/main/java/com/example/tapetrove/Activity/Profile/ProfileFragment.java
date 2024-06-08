package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private Button btEdit, btOut;
    private LinearLayout menuRent, menuWishlist, menuEditProfile, menuDelAcc, menuSignOut;
    TextView tvUsername, tvEmail;
    DatabaseReference useRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Menampilkan Informasi Akun
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String email = user.getEmail();
            tvEmail.setText(email);

            useRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
            useRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String username = snapshot.child("username").getValue(String.class);
                        tvUsername.setText(username);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    tvUsername.setText("Unknown");
                }
            });
        }

        // Inisialisasi menu
        menuRent = view.findViewById(R.id.menuRent);
        menuWishlist = view.findViewById(R.id.menuWishlist);
        menuEditProfile = view.findViewById(R.id.menuEditProfile);
        menuDelAcc = view.findViewById(R.id.menuDelAcc);
        menuSignOut = view.findViewById(R.id.menuSignOut);
        menuRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new HistoryFragment());
            }
        });

        menuWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new WishlistFragment());
            }
        });

        menuEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new EditProfileFragment());
            }
        });

        menuDelAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        menuSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void signOut() {

    }
}
