package com.example.tapetrove;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WishlistDAO {
    @Query("SELECT * FROM film")
    List<FilmWishlistEntity> getAll();

    @Insert
    void insert(FilmWishlistEntity film);

    @Delete
    void delete(FilmWishlistEntity film);
}

