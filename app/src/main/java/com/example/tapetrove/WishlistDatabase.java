package com.example.tapetrove;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FilmWishlistEntity.class}, version = 1)
public abstract class WishlistDatabase extends RoomDatabase {
    public abstract WishlistDAO wishlistDAO();
}
