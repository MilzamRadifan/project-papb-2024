package com.example.tapetrove;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private RecyclerView rvFilm;
  private RecyclerView rvPosterNewReleased;
  private RecyclerView rvPosterTrending;
  private RecyclerView rvPosterUpcoming;
  private RecyclerView rvPosterTopRating;



  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public HomeFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment HomeFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static HomeFragment newInstance(String param1, String param2) {
    HomeFragment fragment = new HomeFragment();
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
    View view =inflater.inflate(R.layout.fragment_home, container, false);

    rvFilm = view.findViewById(R.id.rvFilm);
    rvPosterNewReleased = view.findViewById(R.id.rvPosterNewReleased);
    rvPosterTrending = view.findViewById(R.id.rvPosterTrending);
    rvPosterUpcoming = view.findViewById(R.id.rvPosterUpcoming);
    rvPosterTopRating = view.findViewById(R.id.rvPosterTopRating);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    /* Menjalankan thread untuk mengambil data sedan tayang dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan poster horizontal dari data yang telah diambil menggunakan RecyclerView */
    Handler hTopPoster = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        FilmAdapter filmAdapter = new FilmAdapter(getContext(), results);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvFilm);
        rvFilm.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvFilm.setAdapter(filmAdapter);
      }
    };
    Thread tTopPoster = new FilmThread(hTopPoster, 3, "now_playing");
    tTopPoster.start();

    /* Menjalankan thread untuk mengambil data film baru dirilis dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hNewReleased = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), results);
        rvPosterNewReleased.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPosterNewReleased.setAdapter(movieAdapter);
      }
    };
    Thread tNewReleased = new FilmThread(hNewReleased, 1, "now_playing");
    tNewReleased.start();


    /* Menjalankan thread untuk mengambil data film trending dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hTrending = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), results);
        rvPosterTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPosterTrending.setAdapter(movieAdapter);
      }
    };
    Thread tTrending = new FilmThread(hTrending, 2, "popular");
    tTrending.start();


    /* Menjalankan thread untuk mengambil data film upcoming dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hUpcoming = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), results);
        rvPosterUpcoming.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPosterUpcoming.setAdapter(movieAdapter);
      }
    };
    Thread tUpcoming = new FilmThread(hUpcoming, 1, "upcoming");
    tUpcoming.start();


    /* Menjalankan thread untuk mengambil data film top rating dari API TMDB menggunakan HTTPURLCONNECTION */
    /* Serta menampilkan data yang telah diambil menggunakan RecyclerView */
    Handler hTopRating = new Handler(Looper.getMainLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MovieResults movieResults = (MovieResults) msg.obj;
        List<MovieResults.ResultsBean> results = movieResults.getResults();
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), results);
        rvPosterTopRating.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPosterTopRating.setAdapter(movieAdapter);
      }
    };
    Thread tTopRating = new FilmThread(hTopRating, 1, "top_rated");
    tTopRating.start();

  }
}