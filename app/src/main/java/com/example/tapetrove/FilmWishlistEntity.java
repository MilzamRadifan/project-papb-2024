package com.example.tapetrove;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
public class FilmWishlistEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idFilm;
    private String judul;
    private String durasi;
    private String tanggalRilis;
    private String genre;

    public FilmWishlistEntity(int idFilm, String judul, String durasi, String tanggalRilis, String genre) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.durasi = durasi;
        this.tanggalRilis = tanggalRilis;
        this.genre = genre;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getIdFilm() {
        return idFilm;
    }
    public String getJudul() {
        return judul;
    }
    public String getDurasi() {
        return durasi;
    }
    public String getTanggalRilis() {
        return tanggalRilis;
    }
    public String getGenre() {
        return genre;
    }
}
