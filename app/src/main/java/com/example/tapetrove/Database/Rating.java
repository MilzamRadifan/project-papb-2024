package com.example.tapetrove.Database;

public class Rating {
    private String key;
    private String userId;
    private int movieId;
    private String username;
    private float rating;
    private String comment;

    public Rating() {
    }

    public Rating(String userId, int movieId, String username, float rating, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
