package com.example.tapetrove;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText etEmail, etUsername, etPassword, etNoTelpon, etAlamat;
    private UserDatabase userDb;
    private UserDao userDao;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance(String username, String email, String password, String noTelpon, String alamat) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("email", email);
        args.putString("password", password);
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
        etPassword = view.findViewById(R.id.etPassword);
        etNoTelpon = view.findViewById(R.id.etnomorhp);
        etAlamat = view.findViewById(R.id.etAlamat);
        Button btSimpan = view.findViewById(R.id.btSimpan);
        Button btCancel = view.findViewById(R.id.btCancel);

        userDb = Room.databaseBuilder(requireContext(), UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        Bundle bundle = getArguments();
        if (bundle != null) {
            String username = bundle.getString("username");
            String email = bundle.getString("email");
            String password = bundle.getString("password");
            String noTelpon = bundle.getString("noTelpon");
            String alamat = bundle.getString("alamat");

            etUsername.setText(username);
            etEmail.setText(email);
            etPassword.setText(password);
            etNoTelpon.setText(noTelpon);
            etAlamat.setText(alamat);
        }

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etUsername.getText().toString();
                String newEmail = etEmail.getText().toString();
                String newPassword = etPassword.getText().toString();
                String newNoTelpon = etNoTelpon.getText().toString();
                String newAlamat = etAlamat.getText().toString();

                User updatedUser = new User(0, newUsername, newEmail, newPassword, newNoTelpon, newAlamat);
                userDao.updateUser(updatedUser);

                Bundle result = new Bundle();
                result.putString("newUsername", newUsername);
                result.putString("newEmail", newEmail);
                result.putString("newNoTelpon", newNoTelpon);
                result.putString("newAlamat", newAlamat);
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