package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {
    RatingBar rb_ratingBar;
    float myRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rating);

        rb_ratingBar = findViewById(R.id.rb_ratingBar);
        rb_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating = (int) v;

                myRating = rb_ratingBar.getRating();
                switch (rating) {
                    case 1:
                        postRating("Saya Kecewa!");
                        break;
                    case 2:
                        postRating("Saya Sedih!");
                        break;
                    case 3:
                        postRating("Biasa Saja!");
                        break;
                    case 4:
                        postRating("Saya Puas!");
                        break;
                    case 5:
                        postRating("Saya Sangat Puas!");
                        break;
                }
            }
        });

        Button btn_submitButton = findViewById(R.id.btn_submitButton);
        btn_submitButton.setOnClickListener(view -> {
            Intent intent = new Intent(RatingActivity.this, ReviewActivity.class);
            intent.putExtra("rating", myRating);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void postRating(String message) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("value", myRating);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, "https://api.themoviedb.org/3/movie/941732/rating", jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            String statusMessage = response.getString("status_message");
                            if (success) {
                                Toast.makeText(RatingActivity.this, statusMessage, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RatingActivity.this, "Failed: " + statusMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RatingActivity.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RatingActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("accept", "application/json");
                headers.put("Content-Type", "application/json;charset=utf-8");
                headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMWZjN2Y5MmExOTcyYTQwMTY3Y2Y1ZGE4ZjYxN2RkNSIsInN1YiI6IjY2MjY1ZmZlN2E5N2FiMDE2MzhkNzMwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uuoscwfkjxJyZ_gx9x8KjZBz64zue2OKfS336Peee6k");
                return headers;
            }
        };

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}