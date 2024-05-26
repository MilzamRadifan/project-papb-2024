package com.example.tapetrove.Activity.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tapetrove.Database.Rating;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("rating").child(firebaseUser.getUid());

        String userName = firebaseUser.getEmail();
        String userId = firebaseUser.getUid();

        float rating;
        if (getArguments() != null) {
            rating = getArguments().getFloat("rating", 0);
            idFilm = getArguments().getInt("idFilm", 0);
            title = getArguments().getString("title", null);
        } else {
            rating = 0;
        }

        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            review = etReview.getText().toString();
            if (!review.isEmpty()) {
                Rating rate = new Rating(userId, idFilm, userName, rating, review);
                databaseReference.push().setValue(rate)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                showToast("Komentar Terkirim! Lanjut Eksplor Film Lainnya yaa");
                            } else {
                                showToast("Gagal mengirim komentar");
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
