package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapetrove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText etEmail, etUsername, etNoTelpon, etAlamat;
//    private DatabaseReference mDatabase;
//    private FirebaseAuth mAuth;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance(String username, String email, String noTelpon, String alamat) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("email", email);
        args.putString("noTelpon", noTelpon);
        args.putString("alamat", alamat);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etNoTelpon = view.findViewById(R.id.etnomorhp);
        etAlamat = view.findViewById(R.id.etAlamat);
        Button btSimpan = view.findViewById(R.id.btSimpan);
        Button btCancel = view.findViewById(R.id.btCancel);

//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        Bundle bundle = getArguments();
        if (bundle != null) {
            String username = bundle.getString("username");
            String email = bundle.getString("email");
            String noTelpon = bundle.getString("noTelpon");
            String alamat = bundle.getString("alamat");

            etUsername.setText(username);
            etEmail.setText(email);
            etNoTelpon.setText(noTelpon);
            etAlamat.setText(alamat);
        }

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etUsername.getText().toString();
                String newEmail = etEmail.getText().toString();
                String newNoTelpon = etNoTelpon.getText().toString();
                String newAlamat = etAlamat.getText().toString();

//                DatabaseReference userRef = mDatabase.child(mAuth.getCurrentUser().getUid());

//                userRef.child("username").setValue(newUsername);
//                userRef.child("email").setValue(newEmail);
//                userRef.child("telephone").setValue(newNoTelpon);
//                userRef.child("address").setValue(newAlamat);

                Bundle result = new Bundle();
                result.putString("newusername", newUsername);
                result.putString("newemail", newEmail);
                result.putString("newnotelpon", newNoTelpon);
                result.putString("newalamat", newAlamat);
                getParentFragmentManager().setFragmentResult("editProfileResult", result);

                // Setelah menyimpan, kembali ke ProfileFragment
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke ProfileFragment
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}