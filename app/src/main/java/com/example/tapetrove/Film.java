package com.example.tapetrove;

import android.graphics.Bitmap;

public class Film {

    public String namaFilm;
    public String genre;
    public Bitmap posterfilm;
    public String paymentMethod;
    public String hargaSewa;
    public String tanggalRent;
    public String tanggalReturn;
    public String status;

    public Film(Bitmap posterfilm, String namaFilm, String genre, String paymentMethod, String hargaSewa, String tanggalRent, String tanggalReturn, String status){
        this.posterfilm = posterfilm;
        this.namaFilm = namaFilm;
        this.genre= genre;
        this.paymentMethod = paymentMethod;
        this.hargaSewa = hargaSewa;
        this.tanggalRent = tanggalRent;
        this.tanggalReturn = tanggalReturn;
        this.status = status;
    }
}
