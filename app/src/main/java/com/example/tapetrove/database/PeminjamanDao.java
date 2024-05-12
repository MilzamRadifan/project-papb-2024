package com.example.tapetrove.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PeminjamanDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(Peminjaman peminjaman);
  @Update()
  void update(Peminjaman peminjaman);
  @Delete()
  void delete(Peminjaman peminjaman);
  @Query("SELECT * from Peminjaman ORDER BY id_peminjaman ASC")
  LiveData<List<Peminjaman>> getAllMovies();

}
