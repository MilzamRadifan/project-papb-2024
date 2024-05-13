package com.example.tapetrove;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ReviewModel.class}, version = 1)
public abstract class ReviewDatabase extends RoomDatabase {
    public abstract ReviewDAO reviewDao();
}
