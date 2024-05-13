package com.example.tapetrove;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private TextView tvIdPeminjaman,tvIdMovie,tvTanggalPeminjaman;


  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProfileFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProfileFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ProfileFragment newInstance(String param1, String param2) {
    ProfileFragment fragment = new ProfileFragment();
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
    View view= inflater.inflate(R.layout.fragment_profile, container, false);

    tvIdPeminjaman=view.findViewById(R.id.tvIdPeminjaman);
    tvIdMovie=view.findViewById(R.id.tvIdMovie);
    tvTanggalPeminjaman=view.findViewById(R.id.tvTanggalPeminjaman);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    PeminjamanViewModel peminjamanViewModel = obtainViewModel((AppCompatActivity) requireActivity());

    peminjamanViewModel.getAllMovies().observe(getActivity(), peminjaman -> {
      if (peminjaman != null) {
//                peminjaman.get(0).getId_peminjaman();
//                peminjaman.get(0).getId_movie();
//                peminjaman.get(0).getTanggal_sewa();
//
//                peminjaman.get(0).getMovie();

        Gson gson = new Gson();
        String jsonFromDatabase = peminjaman.get(peminjaman.size()-1).getMovie();

        MovieResults.ResultsBean objekfilm=gson.fromJson(jsonFromDatabase, MovieResults.ResultsBean.class);

        tvIdPeminjaman.setText(Integer.toString(peminjaman.get(peminjaman.size()-1).getId_peminjaman()));
//                tvIdMovie.setText(Integer.toString(peminjaman.get(1).getId_movie()));
//        tvIdMovie.setText(Integer.toString(objekfilm.getId()));
        tvIdMovie.setText(objekfilm.getTitle());
        tvTanggalPeminjaman.setText(peminjaman.get(peminjaman.size()-1).getTanggal_sewa());
      }
    });

  }



  @NonNull
  private static PeminjamanViewModel obtainViewModel(AppCompatActivity activity) {
    ViewModelFactory factory =
        ViewModelFactory.getInstance(activity.getApplication());

    return new ViewModelProvider(activity,
        factory).get(PeminjamanViewModel.class);
  }
}