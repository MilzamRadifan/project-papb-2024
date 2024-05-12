package com.example.tapetrove.helper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapetrove.ui.PeminjamanViewModel;

public class ViewModelFactory extends
    ViewModelProvider.NewInstanceFactory {
  private static volatile ViewModelFactory INSTANCE;
  private final Application mApplication;
  private ViewModelFactory(Application application) {
    mApplication = application;
  }
  public static ViewModelFactory getInstance(Application application) {
    if (INSTANCE == null) {
      synchronized (ViewModelFactory.class) {
        INSTANCE = new ViewModelFactory(application);
      }
    }
    return INSTANCE;
  }
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(PeminjamanViewModel.class)) {
      return (T) new PeminjamanViewModel(mApplication);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}