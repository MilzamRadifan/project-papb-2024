package com.example.tapetrove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Film {
    public String name;
    public String genre;
    public Bitmap topPoster;

    public Bitmap poster;

    public Boolean upcoming;

    public int duration;
//    public double rating;
//    public int ageRating;
//    public int yearRelease;
//    public String synopsis;
//    public double price;

    public Film(String name, String genre, Bitmap topPoster,Bitmap poster,Boolean upcoming) {
        this.name = name;
        this.genre = genre;
        this.topPoster = topPoster;
        this.poster=poster;
        this.upcoming=upcoming;
    }
    public Film(String name, String genre, Bitmap poster,Boolean upcoming) {
        this.name = name;
        this.genre = genre;
        this.poster = poster;
        this.upcoming=upcoming;
    }

    public static Film createFilmWithDrawable(Context context, String name, String genre, int topPosterId, int posterId,Boolean upcoming) {
        Bitmap topPoster = BitmapFactory.decodeResource(context.getResources(), topPosterId);
        Bitmap poster = BitmapFactory.decodeResource(context.getResources(), posterId);
        return new Film(name, genre, topPoster,poster,upcoming);
    }
    public static Film createFilmWithDrawable(Context context, String name, String genre, int posterId,Boolean upcoming) {

        Bitmap poster = BitmapFactory.decodeResource(context.getResources(), posterId);
        return new Film(name, genre, poster,upcoming);
    }


}
