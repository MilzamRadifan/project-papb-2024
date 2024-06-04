package com.example.tapetrove.Activity.Authentication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

  private EditText etEmail, etPassword;
  private Button btSignIn, btSignUp;
  private FirebaseAuth mAuth;
  private FirebaseDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    btSignIn = findViewById(R.id.btSignin);
    btSignUp = findViewById(R.id.btSignUp);

    mAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();

    btSignIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
          Toast.makeText(SignInActivity.this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
          return;
        }

        login(email, password);
      }
    });

    btSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if(currentUser != null){
      Intent intent = new Intent(SignInActivity.this, MainActivity.class);
      startActivity(intent);
      finish();
    }
  }

  private void login(String email, String password) {
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  Log.d(TAG, "signInWithEmail:success");
                  FirebaseUser user = mAuth.getCurrentUser();
                  Toast.makeText(SignInActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                  startActivity(intent);
                  finish();
                } else {
                  Log.w(TAG, "signInWithEmail:failure", task.getException());
                  Toast.makeText(SignInActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                  // Tambahkan logging error yang lebih detail
                  if (task.getException() != null) {
                    Log.e(TAG, "Error details: ", task.getException());
                  }
                }
              }
            });
  }
}
