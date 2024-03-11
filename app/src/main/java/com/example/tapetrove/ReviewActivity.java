package com.example.tapetrove;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReviewActivity extends AppCompatActivity {

    EditText inputReview;
    TextView textView;
    Button button;
    float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);

        inputReview = findViewById(R.id.et_reviewET);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btn_submitButton);

        rating = getIntent().getFloatExtra("rating", 0);

        button.setOnClickListener(view -> {
            String review = inputReview.getText().toString();
            textView.setText("Rating: " + rating + "\nReview: " + review);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}