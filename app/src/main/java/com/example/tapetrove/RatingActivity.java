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

public class RatingActivity extends AppCompatActivity {
    RatingBar rb_ratingBar;
    float myRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rating);

        rb_ratingBar = (RatingBar) findViewById(R.id.rb_ratingBar);
        rb_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                String message = null;

                myRating = rb_ratingBar.getRating();
                switch (rating) {
                    case 1:
                        message = "Saya Kecewa!";
                        break;
                    case 2:
                        message = "Saya Sedih!";
                        break;
                    case 3:
                        message = "Biasa Saja!";
                        break;
                    case 4:
                        message = "Saya Puas!";
                        break;
                    case 5:
                        message = "Saya Sangat Puas!";
                        break;
                }

                Toast.makeText(RatingActivity.this, message, Toast.LENGTH_SHORT).show();
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

}