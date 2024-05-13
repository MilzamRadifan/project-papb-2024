package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.tapetrove.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvEmail;
    private ImageButton ibAvatar;
    private UserDatabase userDb;
    private UserDao userDao;
    private pilihGambarProfile pilihGambar;
    FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userDb = Room.databaseBuilder(requireContext(), UserDatabase.class, "user")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDb.getDao();

        Bundle userData = getArguments();
        if (userData != null) {
            String username = userData.getString("username");
            String email = userData.getString("email");
            String notelpon = userData.getString("notelpon");
            String address = userData.getString("address");

            binding.tvUsername.setText(username);
            binding.tvEmail.setText(email);
        }

        pilihGambar = new pilihGambarProfile(requireContext(), binding.ibAvatar);

        binding.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment editProfileFragment = EditProfileFragment.newInstance(
                        binding.tvUsername.getText().toString(),
                        binding.tvEmail.getText().toString(),
                        "",
                        "",
                        ""
                );
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frame_Layout, editProfileFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout button click here
            }
        });

        binding.content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new historyRentFragment());
            }
        });

        binding.ibAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar.showDialog();
            }
        });

        getParentFragmentManager().setFragmentResultListener("editProfileResult", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String newUsername = result.getString("newUsername");
                String newEmail = result.getString("newEmail");
                binding.tvUsername.setText(newUsername);
                binding.tvEmail.setText(newEmail);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frame_Layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}