package com.example.tapetrove.Activity.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tapetrove.Database.Rating;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReviewFragment extends Fragment {

    int idFilm;
    String title;
    String review;
    String userName;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference, userRef;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        EditText etReview = view.findViewById(R.id.etReview);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        String userId = firebaseUser.getUid();

        float rating;
        if (getArguments() != null) {
            rating = getArguments().getFloat("rating", 0);
            idFilm = getArguments().getInt("idFilm", 0);
            title = getArguments().getString("title", null);
            review = getArguments().getString("review", null);
        } else {
            rating = 0;
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("ratings").child(String.valueOf(idFilm)).child(userId);
        userRef = FirebaseDatabase.getInstance().getReference("users");

        userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    userName = dataSnapshot.child("username").getValue(String.class);
                } else {
                    System.out.println("Not Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                System.out.println("Database error: " + databaseError.getMessage());
            }
        });

        Button btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            review = etReview.getText().toString();
            if (!review.isEmpty()) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Map<String, Object> updateData = new HashMap<>();
                            updateData.put("rating", rating);
                            updateData.put("comment", review);
                            databaseReference.updateChildren(updateData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            showToast("Review updated");
                                            returnTwice();
                                        }
                                    });
                        } else {
                            Rating rate = new Rating(userId, idFilm, userName, rating, review);
                            databaseReference.setValue(rate)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            showToast("Rating and Review Sent! Let's Explore More Films");
                                            returnTwice();
                                        } else {
                                            showToast("Failed to post, Try again");
                                        }
                                    });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            } else {
                showToast("Komentar tidak boleh kosong");
            }
        });
        return view;
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void returnTwice() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.popBackStack();
    }
}

