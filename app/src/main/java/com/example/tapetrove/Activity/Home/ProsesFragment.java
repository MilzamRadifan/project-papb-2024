package com.example.tapetrove.Activity.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.R;

public class ProsesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1, mParam2;

    public ProsesFragment() {
        // Required empty public constructor
    }

    public static ProsesFragment newInstance(String param1, String param2) {
        ProsesFragment fragment = new ProsesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_proses, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ApiResponse.Movie movie = (ApiResponse.Movie) bundle.getSerializable("film");
            String namaBank = bundle.getString("namaBank");
            String statusPembayaran = "proses";
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Mengakhiri ProsesActivity setelah memulai BerhasilActivity
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    bundle.putString("namaBank", namaBank);
                    bundle.putString("statusPembayaran", statusPembayaran);

                    // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
                    ((MainActivity) getContext()).replaceFragmentWithBundle(new BerhasilFragment(), bundle);
                }
            }, 5000); // 5000 milidetik = 5 detik
        }


    }
}