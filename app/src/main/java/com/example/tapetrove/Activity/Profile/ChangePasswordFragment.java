package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tapetrove.Activity.Authentication.SignInActivity;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

  private EditText etOldPassword,etPassword, etConfirmPassword;
  private Button btSave;
  private DatabaseReference databaseReference;
  public ChangePasswordFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_change_password, container, false);

    etPassword = view.findViewById(R.id.etPassword);
    etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
    etOldPassword = view.findViewById(R.id.etOldPassword);
    btSave = view.findViewById(R.id.btSave);

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    assert user != null;
    databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

    btSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updatePassword();
      }
    });



    return view;
  }

  private void updatePassword() {
    String password = String.valueOf(etPassword.getText());
    String confirmPassword = String.valueOf(etConfirmPassword.getText());
    String oldPassword = String.valueOf(etOldPassword.getText());

    if (password.isEmpty() || confirmPassword.isEmpty() || oldPassword.isEmpty()){
      Toast.makeText(getContext(), "Password and confirmation password must not be empty", Toast.LENGTH_SHORT).show();
      return;
    }

    if (password.equals(confirmPassword)) {

      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      if (user != null) {
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);

        user.reauthenticate(credential)
            .addOnSuccessListener(aVoid -> {
              user.updatePassword(password)
                  .addOnSuccessListener(aVoid1 -> Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show())
                  .addOnFailureListener(e -> Toast.makeText(getContext(), "Password change failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            })
            .addOnFailureListener(e -> Toast.makeText(getActivity(), "Re-authentication failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
      }

      Map<String, Object> updateData = new HashMap<>();
      updateData.put("password", password);

      databaseReference.updateChildren(updateData);
    } else {
      Toast.makeText(getContext(), "Password and confirmation password doesn't match", Toast.LENGTH_SHORT).show();
    }
  }
}