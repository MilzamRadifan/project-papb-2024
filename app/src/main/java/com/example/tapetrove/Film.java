package com.example.tapetrove;

import android.graphics.Bitmap;

public class Film {

    public String paymentMethod;
    public String hargaSewa;
    public String tanggalRent;
    public String tanggalReturn;
    public String status;

    public Film(String paymentMethod, String hargaSewa, String tanggalRent, String tanggalReturn, String status){
        this.paymentMethod = paymentMethod;
        this.hargaSewa = hargaSewa;
        this.tanggalRent = tanggalRent;
        this.tanggalReturn = tanggalReturn;
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getHargaSewa() {
        return hargaSewa;
    }

    public String getTanggalRent() {
        return tanggalRent;
    }

    public String getStatus() {
        return status;
    }
}
