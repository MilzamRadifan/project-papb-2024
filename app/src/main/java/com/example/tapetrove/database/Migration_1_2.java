package com.example.tapetrove.database;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_1_2 extends Migration {

  public Migration_1_2() {
    super(1, 2);
  }

  @Override
  public void migrate(SupportSQLiteDatabase database) {
    // Tambahkan SQL untuk menambahkan kolom baru ke tabel yang ada
    database.execSQL("ALTER TABLE Movie ADD COLUMN movie TEXT");
  }
}
