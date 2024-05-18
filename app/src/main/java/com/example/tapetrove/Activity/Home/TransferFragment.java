package com.example.tapetrove.Activity.Home;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tapetrove.R;
import com.example.tapetrove.Api.MovieResults;
import com.example.tapetrove.Api.Genre;

import java.util.ArrayList;
import java.util.List;

public class TransferFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView setBank,
            setKeterangan,
            setJudul,
            setScore;
    private Button setButton;

    private String mParam1, mParam2;

    public TransferFragment() {
        // Required empty public constructor
    }

    public static TransferFragment newInstance(String param1, String param2) {
        TransferFragment fragment = new TransferFragment();
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
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        setKeterangan = view.findViewById(R.id.tvTransferKeterangan);
        setBank = view.findViewById(R.id.tvTransfer);
        setButton = view.findViewById(R.id.button2);
        setJudul = view.findViewById(R.id.tvTransferTitle);
        setScore = view.findViewById(R.id.tvTransferScore);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            MovieResults.ResultsBean movie = (MovieResults.ResultsBean) bundle.getSerializable("film");
            String namaBank = bundle.getString("namaBank");

            if (getArguments() != null) {
                List<Integer> genre_ids = movie.getGenre_ids();
                List<String> movieGenres = new ArrayList<>();

                if (namaBank != null) {
                    setBank.setText("Transfer Melalui " + namaBank);
                    setButton.setText("Bayar Melalui " + namaBank);
                    setJudul.setText(movie.getTitle());
                    setScore.setText("★ " + String.format("%.1f", movie.getVote_average()));
                    Handler hGenre = new Handler(Looper.getMainLooper()) {
                        String formattedGenre;
                        TextView setGenre = view.findViewById(R.id.tvTransferGenre);

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
                            formattedGenre = movieGenres.get(0);
                            for (int i = 1; i < movieGenres.size(); i++) {
                                formattedGenre = formattedGenre + " | " + movieGenres.get(i);
                            }
                            setGenre.setText(formattedGenre);
                        }

                    };
                    Thread tGenre = new GenreThread(hGenre);
                    tGenre.start();
                    if (namaBank.equals("QRIS")) {
                        setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan bayar melalui QRIS dibawah ini. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                    } else {
                        setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan transfer ke nomor rekening berikut 1480325238. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                    }
                    ImageView ivTransferPoster = view.findViewById(R.id.ivTransferPoster);

                    Glide.with(getActivity())
                            .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                            .into(ivTransferPoster);
                }
            }
            Button button2 = view.findViewById(R.id.button2);

            // Mengatur listener klik pada tombol
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Memulai aktivitas ProsesActivity saat tombol diklik
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film", movie);
                    bundle.putString("namaBank", namaBank);

                    // Panggil metode untuk mengganti fragment dan kirim Bundle ke fragment peminjaman
                    ((MainActivity) getContext()).replaceFragmentWithBundle(new ProsesFragment(), bundle);
                }
            });

            Button button3 = view.findViewById(R.id.button3);

            // Mengatur listener klik pada tombol
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Memulai aktivitas ProsesActivity saat tombol diklik
                    ((MainActivity) getContext()).replaceFragment(new HomeFragment());
                }
            });
        }
    }
}