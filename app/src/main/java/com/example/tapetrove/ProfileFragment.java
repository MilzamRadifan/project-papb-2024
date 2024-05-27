package com.example.tapetrove;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.database.PeminjamanFirebase;
import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
  private TextView tvIdPeminjaman, tvIdMovie, tvTanggalPeminjaman;


  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private FirebaseAuth mAuth;
  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;
  private List<PeminjamanFirebase> peminjamanList;
  private List<String> dataKey;

  private Button btLogout;

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
    View view = inflater.inflate(R.layout.fragment_profile, container, false);

    tvIdPeminjaman = view.findViewById(R.id.tvIdPeminjaman);
    tvIdMovie = view.findViewById(R.id.tvIdMovie);
    tvTanggalPeminjaman = view.findViewById(R.id.tvTanggalPeminjaman);
    mAuth = FirebaseAuth.getInstance();
    btLogout = view.findViewById(R.id.button5);
    mAuth = FirebaseAuth.getInstance();
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("peminjaman").child(mAuth.getUid());

    peminjamanList = new ArrayList<>();
    dataKey = new ArrayList<>();
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //ngambil data dari firebase
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        peminjamanList.clear();
        dataKey.clear();
        for (DataSnapshot peminjamanSnapshot : snapshot.getChildren()) {
          PeminjamanFirebase peminjaman = peminjamanSnapshot.getValue(PeminjamanFirebase.class);
          dataKey.add(peminjamanSnapshot.getKey());
          peminjamanList.add(peminjaman);
        }

        if (!peminjamanList.isEmpty()) {
          PeminjamanFirebase latestPeminjaman = peminjamanList.get(peminjamanList.size() - 1);
          tvIdMovie.setText(String.valueOf(latestPeminjaman.getId_movie()));
          tvTanggalPeminjaman.setText(latestPeminjaman.getTanggal_sewa());
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        Log.e("firebase", "Error getting data", error.toException());
      }
    });

    btLogout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        logOut();
      }
    });


//    PeminjamanViewModel peminjamanViewModel = obtainViewModel((AppCompatActivity) requireActivity());
//
//    peminjamanViewModel.getAllMovies().observe(getActivity(), peminjaman -> {
//      if (peminjaman != null) {
////                peminjaman.get(0).getId_peminjaman();
////                peminjaman.get(0).getId_movie();
////                peminjaman.get(0).getTanggal_sewa();
////
////                peminjaman.get(0).getMovie();
//
//        Gson gson = new Gson();
//        String jsonFromDatabase = peminjaman.get(peminjaman.size()-1).getMovie();
//
//        //MovieResults.ResultsBean objekfilm=gson.fromJson(jsonFromDatabase, MovieResults.ResultsBean.class);
//
//        SingleMovieResults objekfilm=gson.fromJson(jsonFromDatabase,SingleMovieResults.class);
//        tvIdPeminjaman.setText(Integer.toString(peminjaman.get(peminjaman.size()-1).getId_peminjaman()));
//        tvIdMovie.setText(objekfilm.getTitle());
//        tvTanggalPeminjaman.setText(peminjaman.get(peminjaman.size()-1).getTanggal_sewa());
//        btLogout.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            logOut();
//          }
//        });
//
//      }
//    });


  }

  public void logOut() {
    mAuth.signOut();
    Intent intent = new Intent(getContext(), AuthActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
    startActivity(intent);
  }


  @NonNull
  private static PeminjamanViewModel obtainViewModel(AppCompatActivity activity) {
    ViewModelFactory factory =
        ViewModelFactory.getInstance(activity.getApplication());

    return new ViewModelProvider(activity,
        factory).get(PeminjamanViewModel.class);
  }
}