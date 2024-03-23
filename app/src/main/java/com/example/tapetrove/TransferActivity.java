package com.example.tapetrove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TransferActivity extends AppCompatActivity {
    private TextView setBank;
    private TextView setKeterangan;
    private Button setButton;
    private TextView setJudul;

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
        setKeterangan=findViewById(R.id.textView28);
        setBank = findViewById(R.id.textView13);
        setButton =findViewById(R.id.button2);
        setJudul = findViewById(R.id.textView15);

        if (getIntent() != null) {
            String namaBank = getIntent().getStringExtra("namaBank");
            String judulData = getIntent().getStringExtra("judul_data");
            // Set teks pada TextView sesuai dengan nama bank
            if (namaBank != null) {
                setBank.setText("Transfer Melalui "+namaBank);
                setButton.setText("Bayar Melalui "+namaBank);
                setJudul.setText(judulData);
                if (namaBank.equals("QRIS")){
                    setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan bayar melalui QRIS dibawah ini. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                }else{
                    setKeterangan.setText("Anda akan menyewa film ini selama seminggu, dengan biaya penyewaan sebesar Rp.30.000. Segala bentuk keterlambatan pengembalian akan dikenakan denda sebesar Rp.5.000 per-harinya. Jika anda setuju melanjutkan silahkan transfer ke nomor rekening berikut 1480325238. Setelah membayar, transaksi anda akan diproses terlebih dahulu, proses ini akan berlangsung selama beberapa menit. ");
                }
            }
        }

        Button button2 = findViewById(R.id.button2);

        // Mengatur listener klik pada tombol
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas ProsesActivity saat tombol diklik
                Intent intent = new Intent(TransferActivity.this, ProsesActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);

        // Mengatur listener klik pada tombol
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas ProsesActivity saat tombol diklik
                Intent intent = new Intent(TransferActivity.this, PeminjamanActivity.class);
                startActivity(intent);
            }
        });


    }
    public static Intent newIntent(Context context, String namaBank) {
        Intent intent = new Intent(context, TransferActivity.class);
        intent.putExtra("namaBank", namaBank);
        return intent;
    }
}