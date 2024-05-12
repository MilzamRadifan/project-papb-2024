package com.example.tapetrove;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterWishlist adapterWishlist;
    WishlistDatabase wishlistDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.rvData);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterWishlist = new AdapterWishlist(this);
        recyclerView.setAdapter(adapterWishlist);

        wishlistDatabase = Room.databaseBuilder(getApplicationContext(),
                WishlistDatabase.class, "film_database").build();

        new LoadFilmsTask().execute();

        addFilm(new FilmWishlistEntity(112, "Film A", "Durasi: 160 Menit", "Tanggal Rilis: 2023-02-12", "Genre: Action"));
        addFilm(new FilmWishlistEntity(114, "Film B", "Durasi: 103 Menit", "Tanggal Rilis: 2024-04-1", "Genre: Fantasy"));
        addFilm(new FilmWishlistEntity(301, "Film C", "Durasi: 93 Menit", "Tanggal Rilis: 1998-02-10", "Genre: Thriller"));
        addFilm(new FilmWishlistEntity(112, "Film A", "Durasi: 160 Menit", "Tanggal Rilis: 2023-02-12", "Genre: Action"));
        addFilm(new FilmWishlistEntity(114, "Film B", "Durasi: 103 Menit", "Tanggal Rilis: 2024-04-1", "Genre: Fantasy"));
        addFilm(new FilmWishlistEntity(301, "Film C", "Durasi: 93 Menit", "Tanggal Rilis: 1998-02-10", "Genre: Thriller"));
        addFilm(new FilmWishlistEntity(112, "Film A", "Durasi: 160 Menit", "Tanggal Rilis: 2023-02-12", "Genre: Action"));
        addFilm(new FilmWishlistEntity(114, "Film B", "Durasi: 103 Menit", "Tanggal Rilis: 2024-04-1", "Genre: Fantasy"));
        addFilm(new FilmWishlistEntity(301, "Film C", "Durasi: 93 Menit", "Tanggal Rilis: 1998-02-10", "Genre: Thriller"));
    }

    private void addFilm(FilmWishlistEntity film) {
        new InsertFilmTask().execute(film);
    }

    public void deleteFilm(FilmWishlistEntity film) {
        new DeleteFilmTask().execute(film);
    }

    private class LoadFilmsTask extends AsyncTask<Void, Void, List<FilmWishlistEntity>> {
        @Override
        protected List<FilmWishlistEntity> doInBackground(Void... voids) {
            return wishlistDatabase.wishlistDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<FilmWishlistEntity> films) {
            super.onPostExecute(films);
            adapterWishlist.setFilms(films);
        }
    }

    private class InsertFilmTask extends AsyncTask<FilmWishlistEntity, Void, Void> {
        @Override
        protected Void doInBackground(FilmWishlistEntity... films) {
            wishlistDatabase.wishlistDAO().insert(films[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new LoadFilmsTask().execute();
            Toast.makeText(WishlistActivity.this, "Film added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private class DeleteFilmTask extends AsyncTask<FilmWishlistEntity, Void, Void> {
        @Override
        protected Void doInBackground(FilmWishlistEntity... films) {
            wishlistDatabase.wishlistDAO().delete(films[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new LoadFilmsTask().execute();
            Toast.makeText(WishlistActivity.this, "Film deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
