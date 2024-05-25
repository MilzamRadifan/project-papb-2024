package com.example.tapetrove.Activity.Authentication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tapetrove.Activity.Home.MainActivity;
import com.example.tapetrove.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

  private EditText etEmail, etPassword;
  private Button btSignIn, btRegister, btGoogle;
  private TextView tvSignUp;
  private FirebaseAuth mAuth;
  private FirebaseDatabase database;
//  private GoogleSignInClient mGoogleSignInClient;
//  private int RC_SIGN_IN = 20;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    btSignIn = findViewById(R.id.btSignin);
    btRegister = findViewById(R.id.btRegister);
    btGoogle = findViewById(R.id.btGoogle);

    mAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();

//    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail().build();
//
//    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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

//    btGoogle.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPreviousGoogleSignIn();
//      }
//    });

    btRegister.setOnClickListener(new View.OnClickListener() {
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
    // Check if user is signed in (non-null) and update UI accordingly.
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if(currentUser != null){
      Intent intent = new Intent(SignInActivity.this, MainActivity.class);
      startActivity(intent);
      finish();
    }
  }

//  @Override
//  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if(requestCode == RC_SIGN_IN){
//      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//      try {
//        GoogleSignInAccount account = task.getResult(ApiException.class);
//        firbaseAuth(account.getIdToken());
//      }
//      catch (Exception e){
//        Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
//      }
//    }
//  }

//  private void firbaseAuth(String idToken) {
//    AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//    mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//      @Override
//      public void onComplete(@NonNull Task<AuthResult> task) {
//        if(task.isSuccessful()){
//          FirebaseUser user = mAuth.getCurrentUser();
//
//          HashMap<String, Object> map = new HashMap<>();
//          map.put("id",user.getUid());
//          map.put("name", user.getDisplayName());
//          map.put("profile", user.getPhotoUrl().toString());
//
//          database.getReference().child("users").child(user.getUid()).setValue(map);
//
//          Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//          startActivity(intent);
//        }
//        else {
//          Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//        }
//      }
//    });
//  }

//  private void checkPreviousGoogleSignIn() {
//    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//    if (account != null) {
//      mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//          googleSignIn();
//        }
//      });
//    } else {
//      googleSignIn();
//    }
//  }

//  private void googleSignIn() {
//    Intent intent = mGoogleSignInClient.getSignInIntent();
//    startActivityForResult(intent, RC_SIGN_IN);
//  }

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
