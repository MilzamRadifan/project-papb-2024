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
    private LinearLayout content2, content3, content4, content5, content6, content7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btEdit = view.findViewById(R.id.btEdit);
        btOut = view.findViewById(R.id.btOut);

        btEdit.setOnClickListener(v -> navigateToFragment(new EditProfileFragment()));
        btOut.setOnClickListener(v -> {
            // Add logic to handle logout if necessary
        });

//        View.OnClickListener menuClickListener = v -> navigateToFragment(new ComingSoonFragment());

//        content2.setOnClickListener(menuClickListener);
//        content3.setOnClickListener(menuClickListener);
//        content4.setOnClickListener(menuClickListener);
//        content5.setOnClickListener(menuClickListener);
//        content6.setOnClickListener(menuClickListener);
//        content7.setOnClickListener(menuClickListener);

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
