package com.example.tapetrove.Database;

public class Wishlist {
  private String key;
  private String userId;
  private String movieId;
  public Wishlist(){
  }

  public Wishlist(String userId, String movieId) {
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

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }
}
