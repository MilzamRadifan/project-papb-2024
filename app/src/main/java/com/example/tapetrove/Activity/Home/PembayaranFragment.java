package com.example.tapetrove.Activity.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tapetrove.Api.MovieResults;
import com.example.tapetrove.R;
import com.google.android.material.navigation.NavigationView;

public class PembayaranFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1, mParam2;
    private MovieResults.ResultsBean movie;

    public PembayaranFragment() {
        // Required empty public constructor
    }

    public static PembayaranFragment newInstance(String param1, String param2) {
        PembayaranFragment fragment = new PembayaranFragment();
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
        return inflater.inflate(R.layout.fragment_pembayaran, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
            NavigationView navigationView = view.findViewById(R.id.pembayaran_menu);
            // Menangani klik item menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    String namaBank = "";
                    // Menutup drawer setelah item menu diklik
                    if (item.getItemId() == R.id.nav_qris) {
                        // Pindah ke QrisActivity saat item Qris diklik
                        namaBank = "QRIS";
                        startTransferFragment(namaBank);

                    } else if (item.getItemId() == R.id.nav_bni) {
                        // Pindah ke QrisActivity saat item BNI diklik
                        namaBank = "Bank BNI";
                        startTransferFragment(namaBank);

                    } else if (item.getItemId() == R.id.nav_mandiri) {
                        // Pindah ke QrisActivity saat item Mandiri diklik
                        namaBank = "Bank Mandiri";
                        startTransferFragment(namaBank);

                    } else if (item.getItemId() == R.id.nav_bca) {
                        // Pindah ke QrisActivity saat item BCA diklik
                        namaBank = "Bank BCA";
                        startTransferFragment(namaBank);

                    } else if (item.getItemId() == R.id.nav_bri) {
                        // Pindah ke QrisActivity saat item BRI diklik
                        namaBank = "Bank BRI";
                        startTransferFragment(namaBank);
                    }

                    return true;
                }

                private void startTransferFragment(String namaBank) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    bundle.putString("namaBank", namaBank);

                    // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
                    ((MainActivity) getContext()).replaceFragmentWithBundle(new TransferFragment(), bundle);
                }
            });
        }
    }
}