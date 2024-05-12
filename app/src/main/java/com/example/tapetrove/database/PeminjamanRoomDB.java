package com.example.tapetrove.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Peminjaman.class}, version = 3)
public abstract class PeminjamanRoomDB extends RoomDatabase {
  public abstract PeminjamanDao movieDao();
  private static volatile PeminjamanRoomDB INSTANCE;
  public static PeminjamanRoomDB getDatabase(final Context context){
    if (INSTANCE == null){
      synchronized (PeminjamanRoomDB.class){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            PeminjamanRoomDB.class, "movie_db").addMigrations(MIGRATION_2_3).build();
      }
    }
    return INSTANCE;
  }
  private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE Movie ADD COLUMN movie TEXT");
    }
  };

  private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE Movie RENAME TO Peminjaman");
    }
  };
}
