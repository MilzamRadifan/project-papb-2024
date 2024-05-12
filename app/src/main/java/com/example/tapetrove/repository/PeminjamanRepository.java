package com.example.tapetrove.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.database.PeminjamanDao;
import com.example.tapetrove.database.PeminjamanRoomDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PeminjamanRepository {
  private final PeminjamanDao mDaoMovies;

  private final ExecutorService executorService;
  public PeminjamanRepository(Application application) {
    executorService = Executors.newSingleThreadExecutor();
    PeminjamanRoomDB db = PeminjamanRoomDB.getDatabase(application);
    mDaoMovies = db.movieDao();
  }
  public LiveData<List<Peminjaman>> getAllMovies() {
    return mDaoMovies.getAllMovies();
  }
  public void insert(final Peminjaman peminjaman) {
    executorService.execute(() -> mDaoMovies.insert(peminjaman));
  }
  public void delete(final Peminjaman peminjaman){
    executorService.execute(() -> mDaoMovies.delete(peminjaman));
  }
  public void update(final Peminjaman peminjaman){
    executorService.execute(() -> mDaoMovies.update(peminjaman));
  }
}