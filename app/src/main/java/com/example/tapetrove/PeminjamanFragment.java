package com.example.tapetrove;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.database.PeminjamanFirebase;
import com.example.tapetrove.helper.ViewModelFactory;
import com.example.tapetrove.ui.PeminjamanViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
  private FirebaseAuth mAuth;
  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;
  private List<PeminjamanFirebase> peminjamanList;
  Button btnSewa;
  private String title;

  private SingleMovieResults singleMovieResults;

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
    webView = view.findViewById(R.id.wvTrailer);
    btnSewa = view.findViewById(R.id.buttonSewa);
    mAuth = FirebaseAuth.getInstance();
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference();
    peminjamanList = new ArrayList<>();
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Bundle bundle = getArguments();
    if (bundle != null) {
      int idFilm=bundle.getInt("idFilm");
//      MovieResults.ResultsBean movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
      // Gunakan objek Film sesuai kebutuhan
//      String title = movie.getTitle();
//      String voteAverage = "★ " + String.format("%.1f", movie.getVote_average());
//      String releaseDate = movie.getRelease_date();
//      String synopsis = movie.getOverview();
//      Boolean adult = movie.isAdult();



//      if(adult){
//        tvRating.setText("21+");
//      }else {
//        tvRating.setVisibility(View.INVISIBLE);
//      }
////      tvTitle.setText(Integer.toString(bundle.getInt("idFilm")));
//      tvTitle.setText(title);
//      tvScore.setText(voteAverage);
//      tvYear.setText(releaseDate);
//      tvSynopsis.setText(synopsis);



      Handler hSingleMovie = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
          super.handleMessage(msg);
          singleMovieResults = (SingleMovieResults) msg.obj;
          List<SingleMovieResults.GenresBean> listGenre = singleMovieResults.getGenres();

          title = singleMovieResults.getTitle();
          String voteAverage = "★ " + String.format("%.1f", singleMovieResults.getVote_average());
          String releaseDate = singleMovieResults.getRelease_date();
          String synopsis = singleMovieResults.getOverview();
          String setGenre= listGenre.get(0).getName();
          Boolean adult = singleMovieResults.isAdult();

          for (int i = 1; i < listGenre.size() ; i++) {
            setGenre = setGenre + " | " + listGenre.get(i).getName();
          }

          if(adult){
            tvRating.setText("21+");
          }else {
            tvRating.setVisibility(View.INVISIBLE);
          }
//      tvTitle.setText(Integer.toString(bundle.getInt("idFilm")));
          tvTitle.setText(title);
          tvScore.setText(voteAverage);
          tvYear.setText(releaseDate);
          tvSynopsis.setText(synopsis);
          tvGenre.setText(setGenre);

          String dateString = releaseDate;
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

          try {
            // Parsing string tanggal menjadi objek Date
            Date targetDate = dateFormat.parse(dateString);
            // Mendapatkan tanggal saat ini
            Date currentDate = new Date();
            // Mengecek apakah tanggal saat ini lebih besar dari tanggal target

            if (currentDate.before(targetDate)) {
              btnSewa.setEnabled(false);
              btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
              btnSewa.setTextColor(Color.parseColor("#808080"));
              btnSewa.setText("The movie is not yet available");
            }
          } catch (ParseException e) {
            System.out.println("Format tanggal tidak valid.");
            e.printStackTrace();
          }

        }
      };
      Thread tSingleMovie = new SingleMovieThread(hSingleMovie,idFilm);
      tSingleMovie.start();

      Handler hTrailer = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
          super.handleMessage(msg);
          Trailer trailer = (Trailer) msg.obj;
          List<Trailer.ResultsBean> results = trailer.getResults();
          String linkTrailer = "";
          for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getType().equals("Trailer")) {
              linkTrailer = results.get(i).getKey();
              break;
            }
          }
//          WebView webView =view.findViewById(R.id.wvTrailer);

          String video = "<html><body style=\"padding: 0; margin: 0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + linkTrailer + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>";
          //String video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lV1OOlGwExM\"  allowfullscreen></iframe>";
          webView.getSettings().setJavaScriptEnabled(true);
          webView.setWebChromeClient(new CustomWebChromeClient(getActivity()));
          webView.loadData(video, "text/html", "utf-8");
//        bottomNavigationView.setVisibility(View.INVISIBLE);
        }
      };
//      Thread tTrailer = new TrailerThread(hTrailer, Integer.toString(movie.getId()));
      Thread tTrailer = new TrailerThread(hTrailer, Integer.toString(idFilm));
      tTrailer.start();

//      List<Integer> genre_ids = movie.getGenre_ids();
//      List<String> movieGenres = new ArrayList<>();
//      Handler hGenre = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//          super.handleMessage(msg);
//          Genre genre = (Genre) msg.obj;
//          List<Genre.GenresBean> results = genre.getGenres();
//
//          for (Integer genreId : genre_ids) {
//            for (Genre.GenresBean genresBean : results) {
//              if (genresBean.getId() == genreId) {
//                movieGenres.add(genresBean.getName());
//              }
//            }
//          }
//          String setGenre;
//          setGenre = movieGenres.get(0);
//          for (int i = 1; i < movieGenres.size(); i++) {
//            setGenre = setGenre + " | " + movieGenres.get(i);
//          }
//          tvGenre.setText(setGenre);
//        }
//      };
//      Thread tGenre = new GenreThread(hGenre);
//      tGenre.start();



      BottomNavigationView peminjamanMenu = view.findViewById(R.id.peminjaman_menu);
      peminjamanMenu.setOnItemSelectedListener(item -> {
        if (item.getItemId() == R.id.bottom_wishlist) {
//          startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
//          finish();
//          return true;
        } else if (item.getItemId() == R.id.bottom_bagikan) {
          Intent sendIntent = new Intent();
          sendIntent.setAction(Intent.ACTION_SEND);
          sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo sewa film " + title + ", hanya Rp.30.000 di aplikasi TaveTrove");
          sendIntent.setType("text/plain");
          Intent shareIntent = Intent.createChooser(sendIntent, null);
          startActivity(shareIntent);
          return true;
        } else if (item.getItemId() == R.id.bottom_rating) {
//          startActivity(new Intent(getApplicationContext(), RatingActivity.class));
//          finish();
//          return true;
        } else if (item.getItemId() == R.id.bottom_komentar) {
//          startActivity(new Intent(getApplicationContext(), KomentarActivity.class));
//          finish();
//          return true;
        }
        return false;
      });



      btnSewa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//          Intent intent = new Intent(getActivity(), PembayaranActivity.class);
//          intent.putExtra("film", (Serializable) movie);
//          startActivity(intent);
          Bundle bundle = new Bundle();
//          bundle.putSerializable("film", movie);
          bundle.putInt("idFilm",idFilm);
          // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
          ((HomeActivity) getContext()).replaceFragmentWithBundle(new PembayaranFragment(), bundle);
        }
      });

//      PeminjamanViewModel peminjamanViewModel = obtainViewModel((AppCompatActivity) requireActivity());
//      peminjamanViewModel.getAllMovies().observe(getActivity(), peminjaman -> {
//        if (peminjaman != null) {
//          for (int i = 0; i < peminjaman.size(); i++) {
//            if (idFilm==peminjaman.get(i).getId_movie()){
//              btnSewa.setEnabled(false);
//              btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
//              btnSewa.setTextColor(Color.parseColor("#808080"));
//              btnSewa.setText("You've already rent this movie");
//              break;
//            }
//          }
//        }
//      });

//      databaseReference.child("peminjaman").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<DataSnapshot> task) {
//          if (!task.isSuccessful()) {
//            Log.e("firebase", "Error getting data", task.getException());
//          } else {
//            Log.d("firebase", String.valueOf(task.getResult().getValue()));
//            for (DataSnapshot peminjamanSnapshot : task.getResult().getChildren()) {
//              PeminjamanFirebase peminjaman = peminjamanSnapshot.getValue(PeminjamanFirebase.class);
//              peminjamanList.add(peminjaman);
//              if (peminjaman.getId_movie()==idFilm){
//                btnSewa.setEnabled(false);
//                btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
//                btnSewa.setTextColor(Color.parseColor("#808080"));
//                btnSewa.setText("You've already rent this movie");
//                break;
//              }
//            }
//          }
//        }
//      });
      databaseReference.child("peminjaman").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
          peminjamanList.clear();  // Clear the list to avoid duplicate entries
          for (DataSnapshot peminjamanSnapshot : snapshot.getChildren()) {
            PeminjamanFirebase peminjaman = peminjamanSnapshot.getValue(PeminjamanFirebase.class);
            peminjamanList.add(peminjaman);
            if (peminjaman.getId_movie() == idFilm) {
              btnSewa.setEnabled(false);
              btnSewa.setBackgroundColor(Color.parseColor("#ADD8E6"));
              btnSewa.setTextColor(Color.parseColor("#808080"));
              btnSewa.setText("You've already rent this movie");
              break;
            }else{
              btnSewa.setEnabled(true);
              btnSewa.setBackgroundColor(Color.parseColor("#FF095DC2"));
              btnSewa.setTextColor(Color.parseColor("#fbfdff"));
              btnSewa.setText("Rent The Movie");
            }
          }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
          Log.e("firebase", "Error getting data", error.toException());
        }
      });
    }
  }
  @NonNull
  private static PeminjamanViewModel obtainViewModel(AppCompatActivity activity) {
    ViewModelFactory factory =
        ViewModelFactory.getInstance(activity.getApplication());

    return new ViewModelProvider(activity,
        factory).get(PeminjamanViewModel.class);
  }
}