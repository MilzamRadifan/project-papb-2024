package com.example.tapetrove;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeminjamanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeminjamanFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private TextView tvTitle;
  private TextView tvGenre;
  private TextView tvScore;
  private TextView tvRating;
  private TextView tvYear;
  private TextView tvDuration;
  private TextView tvSynopsis;
  private WebView webView;

  public PeminjamanFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment PeminjamanFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static PeminjamanFragment newInstance(String param1, String param2) {
    PeminjamanFragment fragment = new PeminjamanFragment();
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
    View view = inflater.inflate(R.layout.fragment_peminjaman, container, false);
    tvTitle = view.findViewById(R.id.tvTitle);
    tvGenre = view.findViewById(R.id.tvGenre);
    tvScore = view.findViewById(R.id.tvScore);
    tvRating = view.findViewById(R.id.tvRating);
    tvYear = view.findViewById(R.id.tvYear);
    tvDuration = view.findViewById(R.id.tvDuration);
    tvSynopsis = view.findViewById(R.id.tvSynopsis);
    webView =view.findViewById(R.id.wvTrailer);


    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Bundle bundle = getArguments();
    if (bundle != null) {
      MovieResults.ResultsBean movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
      // Gunakan objek Film sesuai kebutuhan
      String title = movie.getTitle();
      String voteAverage = "★ " + String.format("%.1f", movie.getVote_average());
      String releaseDate = movie.getRelease_date();
      String synopsis = movie.getOverview();

      tvTitle.setText(title);
      tvScore.setText(voteAverage);
      tvYear.setText(releaseDate);
      tvSynopsis.setText(synopsis);

      List<Integer> genre_ids = movie.getGenre_ids();
      List<String> movieGenres = new ArrayList<>();

      Handler hTrailer = new Handler (Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
          super.handleMessage(msg);
          Trailer trailer = (Trailer) msg.obj;
          List<Trailer.ResultsBean> results = trailer.getResults();
          String linkTrailer="";
          for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getType().equals("Trailer")){
              linkTrailer=results.get(i).getKey();
              break;
            }
          }
//          WebView webView =view.findViewById(R.id.wvTrailer);

          String video="<html><body style=\"padding: 0; margin: 0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+linkTrailer+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>";
          //String video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lV1OOlGwExM\"  allowfullscreen></iframe>";
          webView.getSettings().setJavaScriptEnabled(true);
          webView.setWebChromeClient(new CustomWebChromeClient(getActivity()));
          webView.loadData(video,"text/html","utf-8");
//        bottomNavigationView.setVisibility(View.INVISIBLE);
        }
      };
      Thread tTrailer =new TrailerThread(hTrailer,Integer.toString(movie.getId()));
      tTrailer.start();

      Handler hGenre = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
          super.handleMessage(msg);
          Genre genre = (Genre) msg.obj;
          List<Genre.GenresBean> results = genre.getGenres();

          for (Integer genreId : genre_ids) {
            for (Genre.GenresBean genresBean : results) {
              if (genresBean.getId() == genreId) {
                movieGenres.add(genresBean.getName());
              }
            }
          }

          String setGenre;
          setGenre = movieGenres.get(0);
          for (int i = 1; i < movieGenres.size(); i++) {
            setGenre = setGenre + " | " + movieGenres.get(i);
          }
          tvGenre.setText(setGenre);
        }
      };
      Thread tGenre = new GenreThread(hGenre);
      tGenre.start();
      Button btnSewa = view.findViewById(R.id.buttonSewa);
      btnSewa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//          Intent intent = new Intent(getActivity(), PembayaranActivity.class);
//          intent.putExtra("film", (Serializable) movie);
//          startActivity(intent);
          Bundle bundle = new Bundle();
          bundle.putSerializable("film", movie);
          // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
          ((HomeActivity) getContext()).replaceFragmentWithBundle(new PembayaranFragment(), bundle);
        }
      });
    }

  }

}