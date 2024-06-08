package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends Fragment {
    EditText etUsername, etnomorhp, etAlamat;
    Button btSimpan;
    DatabaseReference databaseReference;

    public EditProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etnomorhp = view.findViewById(R.id.etnomorhp);
        etAlamat = view.findViewById(R.id.etAlamat);
        btSimpan = view.findViewById(R.id.btSimpan);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String username = snapshot.child("username").getValue(String.class);
                    String noHP = snapshot.child("telephone").getValue(String.class);
                    String alamat = snapshot.child("address").getValue(String.class);

                    etUsername.setText(username);
                    etnomorhp.setText(noHP);
                    etAlamat.setText(alamat);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        return view;
    };

    private void updateUser(){
        String username = String.valueOf(etUsername.getText());
        String noHP = String.valueOf(etnomorhp.getText());
        String alamat = String.valueOf(etAlamat.getText());

        Map<String, Object> updateData = new HashMap<>();
        updateData.put("username", username);
        updateData.put("telephone", noHP);
        updateData.put("address", alamat);

        databaseReference.updateChildren(updateData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Profile has been changed", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Profile changed failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}