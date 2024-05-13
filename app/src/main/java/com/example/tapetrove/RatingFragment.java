package com.example.tapetrove;

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
    RatingBar rbRatingBar;
    Button btnSubmit;
    public RatingFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        tvJudul = view.findViewById(R.id.tvJudul);
        rbRatingBar = view.findViewById(R.id.rbRatingBar);
        btnSubmit = view.findViewById(R.id.btnSubmit);

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

            ((EvalActivity) requireActivity()).showReviewFragment(rating, idFilm, title);
        });

        String urlAPI = "https://api.themoviedb.org/3/movie/1175161";
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
        return view;
    }
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
