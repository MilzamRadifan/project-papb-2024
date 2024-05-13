package com.example.tapetrove;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Insert
    void insert(ReviewModel review);

    @Query("SELECT * FROM reviews")
    List<ReviewModel> getAllReviews();
}
