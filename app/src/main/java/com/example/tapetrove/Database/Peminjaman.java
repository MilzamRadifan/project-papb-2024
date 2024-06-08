package com.example.tapetrove.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Peminjaman {
  private String id_user;
  private int id_movie;
  private int harga_sewa;
  private String metode_pembayaran;
  private String tanggal_sewa;
  private String tanggal_kembali;
  private String tenggat_kembali;
  private String status_peminjaman;
  public Peminjaman(){

  }
  public Peminjaman(String id_user, int id_movie, int harga_sewa, String metode_pembayaran) {
    this.id_user = id_user;
    this.id_movie = id_movie;
    this.harga_sewa = harga_sewa;
    this.metode_pembayaran = metode_pembayaran;
    this.tanggal_sewa = getCurrentDate();
    this.tanggal_kembali = "";
    this.tenggat_kembali = getReturnDue();
    this.status_peminjaman = "Rental";
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

  public static String getCurrentDate() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    return dateFormat.format(date);
  }

  public static String getReturnDue() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 7); // Tambah 7 hari dari tanggal saat ini
    Date returnDate = calendar.getTime();
    return dateFormat.format(returnDate);
  }

}
