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
import android.widget.Button;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.helper.DateHelper;
import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerhasilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerhasilFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
//  TextView textView32, textView33;
  Button button4;
  private Peminjaman moviedb;
  private PeminjamanViewModel peminjamanViewModel;

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public BerhasilFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment BerhasilFragment.
   */
  // TODO: Rename and change types and number of parameters
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
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_berhasil, container, false);

//    textView32=view.findViewById(R.id.textView32);
//    textView33=view.findViewById(R.id.textView33);
    button4=view.findViewById(R.id.button4);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    peminjamanViewModel = obtainViewModel((AppCompatActivity) requireActivity());
    moviedb = new Peminjaman();

    Bundle bundle = getArguments();
    if (bundle != null) {
      MovieResults.ResultsBean movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
      String namaBank = bundle.getString("namaBank");
      String statusPembayaran = bundle.getString("statusPembayaran");

      Gson gson = new Gson();
      String json = gson.toJson(movie);

      moviedb.setId_user(1);
      moviedb.setId_movie(movie.getId());
      moviedb.setMovie(json);
      moviedb.setHarga_sewa(30000);
      moviedb.setMetode_pembayaran(namaBank);
      moviedb.setTanggal_sewa(DateHelper.getCurrentDate());
      moviedb.setTanggal_kembali("");
      moviedb.setTenggat_kembali(DateHelper.getReturnDue());
      moviedb.setStatus_peminjaman("Rental");

      peminjamanViewModel.insert(moviedb);
//      textView33.setText(movie.getTitle());
//      textView32.setText(namaBank);
//      button4.setText(statusPembayaran);
    }
    button4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//                String statusPembayaran = getIntent().getStringExtra("status_pembayaran");
//                statusPembayaran="berhasil";
//                // Kode untuk berpindah ke activity pembayaran
//                Intent intent = new Intent(BerhasilActivity.this, PeminjamanActivity.class); // Gantilah PembayaranActivity.class dengan nama activity pembayaran Anda
//                intent.putExtra("status_pembayaran", statusPembayaran);
//                startActivity(intent);
//        Intent intent = new Intent(BerhasilActivity.this, MainActivity.class);
//        startActivity(intent);
        ((HomeActivity) getContext()).replaceFragment(new HomeFragment());
      }
    });
  }
  @NonNull
  private static PeminjamanViewModel
  obtainViewModel(AppCompatActivity activity) {
    ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
    return new ViewModelProvider(activity, factory).get(PeminjamanViewModel.class);
  }

}