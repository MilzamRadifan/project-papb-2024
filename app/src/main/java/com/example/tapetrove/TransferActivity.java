package com.example.tapetrove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends AppCompatActivity {
    private TextView setBank;
    private TextView setKeterangan;
    private Button setButton;
    private TextView setJudul;
    private TextView setScore;
    private TextView setGenre;
    private ImageView setPoster;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TransferActivity.class);
        // Jika Anda perlu menyertakan data tambahan, Anda dapat menambahkannya di sini
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                // Handle Home item click
                return true;
            } else if (item.getItemId() == R.id.search) {
                // Handle Search item click
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                // Handle Profile item click
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
        setKeterangan=findViewById(R.id.tvTransferKeterangan);
        setBank = findViewById(R.id.tvTransfer);
        setButton =findViewById(R.id.button2);
        setJudul = findViewById(R.id.tvTransferTitle);
        setScore=findViewById(R.id.tvTransferScore);
        MovieResults.ResultsBean movie = (MovieResults.ResultsBean) getIntent().getSerializableExtra("film");
        String namaBank=getIntent().getStringExtra("namaBank");


        if (getIntent() != null) {
//            String namaBank = getIntent().getStringExtra("namaBank");
//            String judulData = getIntent().getStringExtra("judul_data");

            List<Integer> genre_ids = movie.getGenre_ids();
            List<String> movieGenres = new ArrayList<>();
            // Set teks pada TextView sesuai dengan nama bank
            if (namaBank != null) {
                setBank.setText("Transfer Melalui "+namaBank);
                setButton.setText("Bayar Melalui "+namaBank);
                setJudul.setText(movie.getTitle());
                setScore.setText("★ "+ String.format("%.1f", movie.getVote_average()));
                Handler hGenre = new Handler(Looper.getMainLooper()) {
                    String formattedGenre;
                    TextView setGenre=findViewById(R.id.tvTransferGenre);
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
                if (namaBank.equals("QRIS")){
                    setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan bayar melalui QRIS dibawah ini. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                }else{
                    setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan transfer ke nomor rekening berikut 1480325238. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                }
                ImageView ivTransferPoster=findViewById(R.id.ivTransferPoster);;
                Glide.with(TransferActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                    .into(ivTransferPoster);
            }
        }

        Button button2 = findViewById(R.id.button2);

        // Mengatur listener klik pada tombol
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas ProsesActivity saat tombol diklik
                Intent intent = new Intent(TransferActivity.this, ProsesActivity.class);
                intent.putExtra("film",(Serializable) movie);
                intent.putExtra("namaBank", namaBank);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);

        // Mengatur listener klik pada tombol
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas ProsesActivity saat tombol diklik
                Intent intent = new Intent(TransferActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });


    }
    public static Intent newIntent(Context context, String namaBank,MovieResults.ResultsBean movie) {
        Intent intent = new Intent(context, TransferActivity.class);
        intent.putExtra("film", (Serializable)movie);
        intent.putExtra("namaBank", namaBank);
        return intent;
    }
}