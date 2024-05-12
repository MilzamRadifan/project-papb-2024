package com.example.tapetrove;

public class FilmModel {
    private String judul;
    private String durasi;
    private String tanggalRilis;
    private String genre;
    private int idFilm;

    public FilmModel(int idFilm, String judul, String durasi, String tanggalRilis, String genre) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.durasi = durasi;
        this.tanggalRilis = tanggalRilis;
        this.genre = genre;
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

    public int getIdFilm() { return idFilm; }
}

