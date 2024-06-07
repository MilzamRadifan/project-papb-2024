package com.example.tapetrove.Activity.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapetrove.Activity.Profile.ProfileActivity;
import com.example.tapetrove.Activity.Profile.WishlistFragment;
import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.Api.Genre;
import com.example.tapetrove.Api.Trailer;
import com.example.tapetrove.Database.Wishlist;
import com.example.tapetrove.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PeminjamanFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1, mParam2;
    private TextView tvTitle, tvGenre, tvScore, tvRating, tvYear, tvDuration, tvSynopsis;
    private WebView webView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public PeminjamanFragment() {
        // Required empty public constructor
    }

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Wishlist wishlist = new Wishlist();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ApiResponse.Movie movie = (ApiResponse.Movie) bundle.getSerializable("film");
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

                    String video="<html><body style=\"padding: 0; margin: 0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+linkTrailer+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>";
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebChromeClient(new CustomWebChromeClient(getActivity()));
                    webView.loadData(video,"text/html","utf-8");
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

            BottomNavigationView peminjamanMenu = view.findViewById(R.id.peminjaman_menu);
            peminjamanMenu.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.bottom_wishlist) {
                    String userId = firebaseAuth.getCurrentUser().getUid();
                    int movieId = movie.getId();

                    databaseReference.child("wishlist").child(userId)
                            .orderByChild("movieId").equalTo(movieId)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Toast.makeText(getActivity(), "Film sudah ada di wishlist", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Wishlist wishlist = new Wishlist(userId, movieId);
                                        databaseReference.child("wishlist").child(userId).push().setValue(wishlist)
                                                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                                        intent.putExtra("openFragment", "wishlist");
                                                        startActivity(intent);
                                                    }
                                                });
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                    return true;
                } else if (item.getItemId() == R.id.bottom_bagikan) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo sewa film "+title+", hanya Rp.30.000 di aplikasi TaveTrove");
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                    return true;
                } else if (item.getItemId() == R.id.bottom_rating) {
                    Bundle bundleRating = new Bundle();
                    bundleRating.putInt("idFilm", movie.getId());
                    ((MainActivity) getContext()).replaceFragmentWithBundle(new RatingFragment(), bundleRating);
                    return true;
                }
                return false;
            });
            btnSewa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
                    ((MainActivity) getContext()).replaceFragmentWithBundle(new PembayaranFragment(), bundle);
                }
            });
        }

    }

}