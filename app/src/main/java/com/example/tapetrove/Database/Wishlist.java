package com.example.tapetrove.Database;

public class Wishlist {
  private String key;
  private String userId;
  private int movieId;
  public Wishlist(){
  }

  public Wishlist(String userId, int movieId) {
    this.userId = userId;
    this.movieId = movieId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }
}
