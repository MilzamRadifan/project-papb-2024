package com.example.tapetrove.Activity.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.Database.Peminjaman;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class BerhasilFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button button4;
//    private Peminjaman moviedb;
//    private PeminjamanViewModel peminjamanViewModel;
    private String mParam1, mParam2;

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public BerhasilFragment() {
        // Required empty public constructor
    }

    public static BerhasilFragment newInstance(String param1, String param2) {
        BerhasilFragment fragment = new BerhasilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berhasil, container, false);
        button4=view.findViewById(R.id.button4);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("peminjaman").child(mAuth.getUid());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        if (bundle != null) {
            ApiResponse.Movie movie = (ApiResponse.Movie) bundle.getSerializable("film");
            String metodePembayaran = bundle.getString("namaBank");
            String statusPembayaran = bundle.getString("statusPembayaran");

//            Gson gson = new Gson();
//            String json = gson.toJson(movie);

            Peminjaman peminjaman =new Peminjaman(mAuth.getUid(),movie.getId(),30000,metodePembayaran);
            databaseReference.push().setValue(peminjaman).addOnSuccessListener((MainActivity) getContext(), new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText((MainActivity) getContext(), "Add data", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener((MainActivity) getContext(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed to Add data", Toast.LENGTH_SHORT).show();
                }
            });
        }
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).replaceFragment(new HomeFragment());
            }
        });
    }

}