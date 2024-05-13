package com.example.tapetrove;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class EvalActivity extends AppCompatActivity {
    private RatingFragment ratingFragment;
    private ReviewFragment reviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval);

        ratingFragment = new RatingFragment();
        reviewFragment = new ReviewFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ratingFragment)
                .commit();
    }

    public void showReviewFragment(float rating, int idFilm, String title) {
        Bundle bundle = new Bundle();
        bundle.putFloat("rating", rating);
        bundle.putInt("idFilm", idFilm);
        bundle.putString("title", title);
        reviewFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, reviewFragment)
                .addToBackStack(null)
                .commit();
    }
}
