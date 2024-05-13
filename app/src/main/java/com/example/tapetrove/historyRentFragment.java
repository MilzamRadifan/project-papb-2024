package com.example.tapetrove;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link historyRentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class historyRentFragment extends Fragment {

    private List<Film> films;
    private RecyclerView recyclerView;

    public historyRentFragment() {
        // Required empty public constructor
    }

    public static historyRentFragment newInstance() {
        return new historyRentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_rent, container, false);
        recyclerView = view.findViewById(R.id.rvHistory);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();
        FilmAdapter filmAdapter = new FilmAdapter(getContext(), films);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(filmAdapter);
    }

    private void dataInitialize() {
        films = new ArrayList<>();
        Film a = new Film(BitmapFactory.decodeResource(getResources(), R.drawable.megan), "MEGAN", "Genre: Horror, Thriller, Mystery",
                "QRIS", "30.000", "25-08-2024", "28-08-2024", "Rental");
        Film b = new Film(BitmapFactory.decodeResource(getResources(), R.drawable.zootopia), "ZOOTOPIA", "Genre: Animasi, Comedi",
                "BNI", "28.000", "05-03-2024", "08-03-2024", "Done");
        Film c = new Film(BitmapFactory.decodeResource(getResources(), R.drawable.luca), "LUCA", "Genre: Animasi, Fantasy ",
                "MANDIRI", "35.000", "25-02-2024", "28-02-2024", "Done");

        for (int i = 0; i < 10; i++) {
            films.add(a);
            films.add(b);
            films.add(c);
        }
    }
}