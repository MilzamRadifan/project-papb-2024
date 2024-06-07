package com.example.tapetrove.Activity.Profile;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.tapetrove.R;

public class ProfileFragment extends Fragment {

    private Button btEdit, btOut;
    private LinearLayout menuRent, menuWishlist, menuEditProfile, menuDelAcc, menuSignOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
// Inisialisasi menu
        menuRent = view.findViewById(R.id.menuRent);
        menuWishlist = view.findViewById(R.id.menuWishlist);
        menuEditProfile = view.findViewById(R.id.menuEditProfile);
        menuDelAcc = view.findViewById(R.id.menuDelAcc);
        menuSignOut = view.findViewById(R.id.menuSignOut);
        menuRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new HistoryFragment());
            }
        });

        menuWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new WishlistFragment());
            }
        });

        menuEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new EditProfileFragment());
            }
        });

        menuDelAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        menuSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void signOut() {

    }
}
