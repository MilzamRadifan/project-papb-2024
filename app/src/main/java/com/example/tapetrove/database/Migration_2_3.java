package com.example.tapetrove.database;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_2_3 extends Migration {

  public Migration_2_3() {
    super(2, 3);
  }

  @Override
  public void migrate(SupportSQLiteDatabase database) {
    // Tambahkan SQL untuk menambahkan kolom baru ke tabel yang ada
    database.execSQL("ALTER TABLE Movie RENAME TO Peminjaman");
  }
}