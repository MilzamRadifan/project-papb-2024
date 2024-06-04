package com.example.tapetrove.Activity.Home;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tapetrove.Api.ApiResponse;
import com.example.tapetrove.Api.Genre;
import com.example.tapetrove.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView setBank;
    private TextView setKeterangan;
    private Button setButton;
    Button button;
    private TextView setJudul;
    private TextView setScore;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 1;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        button = view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ApiResponse.Movie movie = (ApiResponse.Movie) bundle.getSerializable("film");
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

                        button.setVisibility(View.VISIBLE);
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


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadImage();
                }
            });

            Button button2 = view.findViewById(R.id.button2);

            // Mengatur listener klik pada tombol
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Memulai aktivitas ProsesActivity saat tombol diklik
//          Intent intent = new Intent(TransferActivity.this, ProsesActivity.class);
//          intent.putExtra("film",(Serializable) movie);
//          intent.putExtra("namaBank", namaBank);
//          startActivity(intent);
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
//          Intent intent = new Intent(TransferActivity.this, MainActivity.class);
//
//          startActivity(intent);
                    ((MainActivity) getContext()).replaceFragment(new HomeFragment());
                }
            });
        }

    }
    private void downloadImage() {
        // Decode the image from drawable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qris_tavetrove);

        // Save the image to external storage
        String fileName = "qris_tavetrove.jpg";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Toast.makeText(getActivity(), "Image downloaded successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Failed to download image.", Toast.LENGTH_SHORT).show();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadImage();
            } else {
                Toast.makeText(getActivity(), "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }
}