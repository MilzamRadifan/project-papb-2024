package com.example.tapetrove;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ReviewFragment extends Fragment {

    TextView textView;
    EditText inputReview;
    float rating;
    int idFilm;
    String title;
    String userName;
    String review;
    ReviewDAO reviewDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        EditText etReview = view.findViewById(R.id.etReview);

        if (getArguments() != null) {
            rating = getArguments().getFloat("rating", 0);
            idFilm = getArguments().getInt("idFilm", 0);
            title = getArguments().getString("title", null);
        }

        userName = "Jono";

        ReviewDatabaseClient client = ReviewDatabaseClient.getInstance(requireContext().getApplicationContext());
        reviewDAO = client.getReviewDatabase().reviewDao();

        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            review = etReview.getText().toString();
            if (!review.isEmpty()) {
                ReviewModel reviewModel = new ReviewModel(title, userName, rating, review);
                insertReview(reviewModel);
                showToast("Komentar Terkirim! Lanjut Eksplor Film Lainnya yaa");
            } else {
                showToast("Komentar tidak boleh kosong");
            }
        });
        return view;
    }

    private void insertReview(ReviewModel review) {
        new Thread(() -> {
            reviewDAO.insert(review);
        }).start();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

