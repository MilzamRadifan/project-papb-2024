package com.example.tapetrove;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProsesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProsesFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProsesFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProsesFragment.
   */
  // TODO: Rename and change types and number of parameters
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
      //MovieResults.ResultsBean movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
      String namaBank = bundle.getString("namaBank");
      String statusPembayaran = "proses";
      int idFilm=bundle.getInt("idFilm");
      TextView tv29 = view.findViewById(R.id.textView29);

      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          // Memulai aktivitas BerhasilActivity setelah 5 detik
//          Intent intent = new Intent(ProsesActivity.this, BerhasilActivity.class);
//          intent.putExtra("statusPembayaran", statusPembayaran);
//          intent.putExtra("film",(Serializable) movie);
//          intent.putExtra("namaBank", namaBank);
//          startActivity(intent);
//          finish(); // Mengakhiri ProsesActivity setelah memulai BerhasilActivity
          Bundle bundle = new Bundle();
//          bundle.putSerializable("film", movie);
          bundle.putString("namaBank", namaBank);
          bundle.putString("statusPembayaran", statusPembayaran);
          bundle.putInt("idFilm",idFilm);

          // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
          ((HomeActivity) getContext()).replaceFragmentWithBundle(new BerhasilFragment(), bundle);
        }
      }, 5000); // 5000 milidetik = 5 detik
    }


  }
}