package com.example.tapetrove.Activity.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapetrove.Database.Rating;
import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RatingFragment extends Fragment {
    float rating = 0;
    String title;
    int idFilm;
    TextView tvJudul;
    String review;
    RatingBar rbRatingBar;
    Button btnSubmit;
    Button btnDeleteRating;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    public RatingFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            idFilm = getArguments().getInt("idFilm", 0);
        }

        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        tvJudul = view.findViewById(R.id.tvJudul);
        rbRatingBar = view.findViewById(R.id.rbRatingBar);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnDeleteRating = view.findViewById(R.id.btnDeleteRating);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("rating").child(firebaseUser.getUid());

        rbRatingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> rating = v);

        btnSubmit.setOnClickListener(v -> {
            if (rating == 1) {
                showToast("Mengecewakan");
            } else if (rating == 2) {
                showToast("Sedikit Mengecewakan");
            } else if (rating == 3) {
                showToast("Biasa Saja");
            } else if (rating == 4) {
                showToast("Woww, Bagus");
            } else if (rating == 5) {
                showToast("Film ini Luar Biasa");
            }

            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putInt("idFilm", idFilm);
            bundle.putFloat("rating", rating);
            bundle.putString("review", review);

            ReviewFragment reviewFragment = new ReviewFragment();
            reviewFragment.setArguments(bundle);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, reviewFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnDeleteRating.setOnClickListener(v -> {
            databaseReference.child(String.valueOf(idFilm)).removeValue()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            showToast("Rating deleted successfully");
                            rbRatingBar.setRating(0);
                        } else {
                            showToast("Failed to delete rating");
                        }
                    });
        });

        fetchMovieDetails();
        checkExistingRating();

        return view;
    }

    private void fetchMovieDetails() {
        String urlAPI = "https://api.themoviedb.org/3/movie/" + idFilm;
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMWZjN2Y5MmExOTcyYTQwMTY3Y2Y1ZGE4ZjYxN2RkNSIsInN1YiI6IjY2MjY1ZmZlN2E5N2FiMDE2MzhkNzMwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uuoscwfkjxJyZ_gx9x8KjZBz64zue2OKfS336Peee6k";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlAPI)
                .header("Authorization", "Bearer " + token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    title = jsonObject.getString("title");
                    idFilm = jsonObject.getInt("id");

                    getActivity().runOnUiThread(() -> {
                        tvJudul.setText(title);
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkExistingRating() {
        databaseReference.child(String.valueOf(idFilm)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Rating existingRating = snapshot.getValue(Rating.class);
                    if (existingRating != null) {
                        rating = existingRating.getRating();
                        rbRatingBar.setRating(rating);
                        btnDeleteRating.setVisibility(View.VISIBLE);
                        review = existingRating.getComment();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast("Failed to check existing rating");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

