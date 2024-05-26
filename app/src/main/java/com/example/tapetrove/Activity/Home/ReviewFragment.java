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

import com.example.tapetrove.Database.Rating;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReviewFragment extends Fragment {

    int idFilm;
    String title;
    String review;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        EditText etReview = view.findViewById(R.id.etReview);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("rating").child(String.valueOf(idFilm));

        String userName = firebaseUser.getEmail();
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

        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        etReview.setText(review);

        btnSubmit.setOnClickListener(v -> {
            review = etReview.getText().toString();
            if (!review.isEmpty()) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Rating existingRating = dataSnapshot.getValue(Rating.class);
                            if (existingRating != null) {
                                existingRating.setRating(rating);
                                existingRating.setComment(review);
                                databaseReference.setValue(existingRating)
                                        .addOnCompleteListener(task -> {
                                            if (task.isSuccessful()) {
                                                showToast("Komentar Diperbarui! Lanjut Eksplor Film Lainnya yaa");
                                            } else {
                                                showToast("Gagal mengirim komentar");
                                            }
                                        });
                            }
                        } else {
                            Rating rate = new Rating(userId, idFilm, userName, rating, review);
                            databaseReference.setValue(rate)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            showToast("Komentar Terkirim! Lanjut Eksplor Film Lainnya yaa");
                                        } else {
                                            showToast("Gagal mengirim komentar");
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
}

