package com.example.tapetrove.database;



public class PeminjamanFirebase {
  private String id_user;
  private int id_movie;
  private int harga_sewa;
  private String metode_pembayaran;
  private String tanggal_sewa;
  private String tanggal_kembali;
  private String tenggat_kembali;
  private String status_peminjaman;

  public PeminjamanFirebase(){

  }

  public PeminjamanFirebase(String id_user, int id_movie, int harga_sewa, String metode_pembayaran, String tanggal_sewa, String tanggal_kembali, String tenggat_kembali, String status_peminjaman) {
    this.id_user = id_user;
    this.id_movie = id_movie;
    this.harga_sewa = harga_sewa;
    this.metode_pembayaran = metode_pembayaran;
    this.tanggal_sewa = tanggal_sewa;
    this.tanggal_kembali = tanggal_kembali;
    this.tenggat_kembali = tenggat_kembali;
    this.status_peminjaman = status_peminjaman;
  }

  public String getId_user() {
    return id_user;
  }

  public void setId_user(String id_user) {
    this.id_user = id_user;
  }

  public int getId_movie() {
    return id_movie;
  }

  public void setId_movie(int id_movie) {
    this.id_movie = id_movie;
  }

  public int getHarga_sewa() {
    return harga_sewa;
  }

  public void setHarga_sewa(int harga_sewa) {
    this.harga_sewa = harga_sewa;
  }

  public String getMetode_pembayaran() {
    return metode_pembayaran;
  }

  public void setMetode_pembayaran(String metode_pembayaran) {
    this.metode_pembayaran = metode_pembayaran;
  }

  public String getTanggal_sewa() {
    return tanggal_sewa;
  }

  public void setTanggal_sewa(String tanggal_sewa) {
    this.tanggal_sewa = tanggal_sewa;
  }

  public String getTanggal_kembali() {
    return tanggal_kembali;
  }

  public void setTanggal_kembali(String tanggal_kembali) {
    this.tanggal_kembali = tanggal_kembali;
  }

  public String getTenggat_kembali() {
    return tenggat_kembali;
  }

  public void setTenggat_kembali(String tenggat_kembali) {
    this.tenggat_kembali = tenggat_kembali;
  }

  public String getStatus_peminjaman() {
    return status_peminjaman;
  }

  public void setStatus_peminjaman(String status_peminjaman) {
    this.status_peminjaman = status_peminjaman;
  }


}
