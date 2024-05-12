package com.example.tapetrove.ui;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tapetrove.database.Peminjaman;
import com.example.tapetrove.repository.PeminjamanRepository;

import java.util.List;

public class PeminjamanViewModel extends ViewModel {
  private final PeminjamanRepository mPeminjamanRepository;
  public PeminjamanViewModel(Application application) {
    mPeminjamanRepository = new PeminjamanRepository(application);
  }
  public void insert(Peminjaman peminjaman) {
    mPeminjamanRepository.insert(peminjaman);
  }
  public void update(Peminjaman peminjaman) {
    mPeminjamanRepository.update(peminjaman);
  }
  public void delete(Peminjaman peminjaman) {
    mPeminjamanRepository.delete(peminjaman);
  }
  public LiveData<List<Peminjaman>> getAllMovies() {
    return mPeminjamanRepository.getAllMovies();
  }
}
