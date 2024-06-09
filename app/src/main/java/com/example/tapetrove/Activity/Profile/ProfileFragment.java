package com.example.tapetrove.Activity.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapetrove.Activity.Authentication.SignInActivity;
import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    private LinearLayout menuRent, menuWishlist, menuEditProfile, menuDelAcc, menuSignOut, menuChangePass;
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
        if (user != null) {
            String email = user.getEmail();
            tvEmail.setText(email);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    useRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
                    useRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String username = snapshot.child("username").getValue(String.class);
                                new Handler(Looper.getMainLooper()).post(() -> tvUsername.setText(username));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            new Handler(Looper.getMainLooper()).post(() -> tvUsername.setText("Unknown"));
                        }
                    });
                }
            }).start();
        }

        // Inisialisasi menu
        menuRent = view.findViewById(R.id.menuRent);
        menuWishlist = view.findViewById(R.id.menuWishlist);
        menuEditProfile = view.findViewById(R.id.menuEditProfile);
        menuDelAcc = view.findViewById(R.id.menuDelAcc);
        menuSignOut = view.findViewById(R.id.menuSignOut);
        menuChangePass = view.findViewById(R.id.menuChangePass);

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

        menuChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new ChangePasswordFragment());
            }
        });

        menuDelAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount();
            }
        });

        menuSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), SignInActivity.class));
            }
        });

        return view;
    }

    private void deleteAccount() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
            databaseReference.removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(), SignInActivity.class));
                                }
                            }
                        });
                } else {
                    Toast.makeText(getActivity(), "Failed to delete user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
