package com.example.tapetrove;

import android.content.Context;
import androidx.room.Room;

public class ReviewDatabaseClient {
    private static ReviewDatabaseClient mInstance;
    private ReviewDatabase reviewDatabase;

    private ReviewDatabaseClient(Context context) {
        reviewDatabase = Room.databaseBuilder(context, ReviewDatabase.class, "ReviewDatabase").build();
    }

    public static synchronized ReviewDatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ReviewDatabaseClient(context);
        }
        return mInstance;
    }

    public ReviewDatabase getReviewDatabase() {
        return reviewDatabase;
    }
}
