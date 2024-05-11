package com.example.tapetrove.profile;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public abstract UserDao userDao();

  private static AppDatabase INSTANCE;

  public static AppDatabase getInstance(Context context){
    if (INSTANCE == null) {
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "UserDB")
              .allowMainThreadQueries()
              .build();
    }
    return INSTANCE;
  }
}
