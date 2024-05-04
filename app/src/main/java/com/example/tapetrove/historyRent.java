package com.example.tapetrove;

import static com.example.tapetrove.TmdbApi.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class historyRent extends AppCompatActivity {

    private List<Film> list;
    private List<FilmApi> data;
    private RecyclerView rvHistory;
    private FilmAdapter filmAdapter;
    public static String BASE_URL = "https://api.themoviedb.org";
    public static String API_KEY = "2abd706b1888d8efd777765ab9edc058";
    public static String LANGUAGE = "en-US";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_rent);

        this.list = new ArrayList<Film>();
        Film a = new Film( "QRIS","30.000","25-08-2024", "28-08-2024",  "Rental");
        Film b = new Film("BNI", "28.000", "05-03-2024", "08-03-2024", "Done");
        Film c = new Film("MANDIRI", "35.000", "25-02-2024", "28-02-2024", "Done");

        for (int i = 0; i < 10; i++) {
            list.add(a);
            list.add(b);
            list.add(c);
        }

        this.data = new ArrayList<>();

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        filmAdapter = new FilmAdapter(historyRent.this, this.data);
        rvHistory.setAdapter(filmAdapter);

        Button btbalik = findViewById(R.id.btbalik);
        btbalik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(historyRent.this, profile.class);
                startActivity(intent);
                finish();
            }
        });
        loadFilms();
    }

    public void loadFilms() {
        TmdbApi api = TmdbApi.getInstance();
        ApiService service = api.getApiService();

        Call<FilmApi> call = service.getMovies("popular", API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<FilmApi>() {
            @Override
            public void onResponse(Call<FilmApi> call, Response<FilmApi> response) {
                if (response.isSuccessful()) {
                    FilmApi filmApi = response.body();
                    if (filmApi != null) {
                        filmAdapter.setFilms(filmApi.getResults());
                    }
                } else {
                    // Handle unsuccessful response
                }
            }
            @Override
            public void onFailure(Call<FilmApi> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
