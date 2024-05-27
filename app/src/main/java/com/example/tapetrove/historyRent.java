//package com.example.tapetrove;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.PopupWindow;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class historyRent extends AppCompatActivity {
//
//    private List<Film> data;
//    private RecyclerView rvHistory;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_rent);
//
//        this.data = new ArrayList<Film>();
//
//        Film a = new Film( BitmapFactory.decodeResource(getResources(), R.drawable.megan),"MEGAN", "Genre: Horror, Thriller, Mystery",
//                "QRIS", "30.000", "25-08-2024", "28-08-2024", "Rental");
//        Film b = new Film(BitmapFactory.decodeResource(getResources(), R.drawable.zootopia), "ZOOTOPIA", "Genre: Animasi, Comedi",
//                "BNI", "28.000", "05-03-2024", "08-03-2024", "Done");
//        Film c = new Film(BitmapFactory.decodeResource(getResources(), R.drawable.luca), "LUCA", "Genre: Animasi, Fantasy ",
//                "MANDIRI", "35.000", "25-02-2024", "28-02-2024", "Done");
//
//        for (int i = 0; i < 10; i++) {
//            data.add(a);
//            data.add(b);
//            data.add(c);
//        }
//
//        this.rvHistory = findViewById(R.id.rvHistory);
//        FilmAdapter filmAdapter = new FilmAdapter(historyRent.this, this.data);
//
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(historyRent.this);
//        this.rvHistory.setLayoutManager(lm);
//        this.rvHistory.setAdapter(filmAdapter);
//
//        FloatingActionButton btbalik = findViewById(R.id.btbalik);
//        btbalik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigasi ke halaman profil
//                Intent intent = new Intent(historyRent.this, profile.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
