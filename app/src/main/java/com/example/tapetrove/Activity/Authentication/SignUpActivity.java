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

import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
  private EditText etEmail, etPassword, etPassConfirm, etUsername, etAddress, etTelephone;
  private Button btSignUp;
  private String email, password, passwordConfirm, username, address, telephone;
  private FirebaseAuth mAuth;
  private FirebaseDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    mAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();

    etUsername = findViewById(R.id.etUsername);
    etAddress = findViewById(R.id.etAddress);
    etTelephone = findViewById(R.id.etTelephone);
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    etPassConfirm = findViewById(R.id.etPassConfirm);

    btSignUp = findViewById(R.id.btRegister);
    btSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        username = etUsername.getText().toString();
        address = etAddress.getText().toString();
        telephone = etTelephone.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        passwordConfirm = etPassConfirm.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
          Toast.makeText(SignUpActivity.this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
          return;
        }

        if (password.equals(passwordConfirm)) {
          SignUp(username, address, telephone, email, password);
        } else {
          Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        
      }
    });
  }

//  private void SignUp(String email, String password) {
//    mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//              @Override
//              public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                  Log.d(TAG,
//                          "createUserWithEmail:success");
//                  FirebaseUser user = mAuth.getCurrentUser();
//
//                  HashMap<String, Object> map = new HashMap<>();
//                  map.put("id",user.getUid());
//                  map.put("email", email);
//                  map.put("password", password);
//                  // bisa ditambahkan lagi klo mau nyimpen lebih banyak atribut ke database
//
//                  database.getReference().child("users").child(user.getUid()).setValue(map)
//                          .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                              if (task.isSuccessful()){
//                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                                startActivity(intent);
//                                finish();
//                              } else Toast.makeText(SignUpActivity.this, "Failed to save user data.", Toast.LENGTH_SHORT).show();
//                            }
//                          });
//                } else {
//                  Log.d(TAG,
//                          "createUserWithEmail:failed");
//                  Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//              }
//            });
//  }

  private void SignUp(String username, String address, String telephone, String email, String password) {
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  Log.d(TAG, "createUserWithEmail:success");
                  FirebaseUser user = mAuth.getCurrentUser();

                  HashMap<String, Object> map = new HashMap<>();
                  map.put("id", user.getUid());
                  map.put("username", username);
                  map.put("address", address);
                  map.put("telephone", telephone);
                  map.put("email", email);
                  map.put("password", password);
                  // bisa ditambahkan lagi klo mau nyimpen lebih banyak atribut ke database

                  database.getReference().child("users").child(user.getUid()).setValue(map)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                              if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Registration successful! Please sign in.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                              } else {
                                Toast.makeText(SignUpActivity.this, "Failed to save user data.", Toast.LENGTH_SHORT).show();
                              }
                            }
                          });
                } else {
                  Log.d(TAG, "createUserWithEmail:failed");
                  Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
              }
            });
  }
}