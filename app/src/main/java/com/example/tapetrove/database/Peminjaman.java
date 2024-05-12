package com.example.tapetrove.database;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Peminjaman implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id_peminjaman")
  private int id_peminjaman;
  @ColumnInfo(name = "id_user")
  private int id_user;
  @ColumnInfo(name = "id_movie")
  private int id_movie;
//  @ColumnInfo(name="movie")
//  private MovieResults.ResultsBean movie;
  @ColumnInfo(name = "movie")
  private String movie;
  @ColumnInfo(name = "harga_sewa")
  private int harga_sewa;
  @ColumnInfo (name="metode_pembayaran")
  private String metode_pembayaran;
  @ColumnInfo(name = "tanggal_sewa")
  private String tanggal_sewa;
  @ColumnInfo(name = "tanggal_kembali")
  private String tanggal_kembali;
  @ColumnInfo(name = "tenggat_kembali")
  private String tenggat_kembali;
  @ColumnInfo(name = "status_peminjaman")
  private String status_peminjaman;



  public int getId_peminjaman() {
    return id_peminjaman;
  }

  public void setId_peminjaman(int id_peminjaman) {
    this.id_peminjaman = id_peminjaman;
  }

  public int getId_user() {
    return id_user;
  }

  public void setId_user(int id_user) {
    this.id_user = id_user;
  }

  public int getId_movie() {
    return id_movie;
  }

  public void setId_movie(int id_movie) {
    this.id_movie = id_movie;
  }

//  public MovieResults.ResultsBean getMovie() {
//    return movie;
//  }
//  public void setMovie(MovieResults.ResultsBean movie) {
//    this.movie = movie;
//  }


  public String getMovie() {
    return movie;
  }

  public void setMovie(String movie) {
    this.movie = movie;
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id_peminjaman);
    dest.writeInt(this.id_user);
    dest.writeInt(this.id_movie);
//    dest.writeSerializable(this.movie);
//    dest.writeParcelable(this.movie, flags);
    dest.writeString(this.movie);
    dest.writeInt(this.harga_sewa);
    dest.writeString(this.metode_pembayaran);
    dest.writeString(this.tanggal_sewa);
    dest.writeString(this.tanggal_kembali);
    dest.writeString(this.tenggat_kembali);
    dest.writeString(this.status_peminjaman);
  }
  @Ignore
  public Peminjaman(){
  }
  public Peminjaman(int id_user, int id_movie, String movie, int harga_sewa, String metode_pembayaran, String tanggal_sewa, String tanggal_kembali, String tenggat_kembali, String status_peminjaman) {
    this.id_user = id_user;
    this.id_movie = id_movie;
    this.movie=movie;

    this.harga_sewa = harga_sewa;
    this.metode_pembayaran=metode_pembayaran;
    this.tanggal_sewa = tanggal_sewa;
    this.tanggal_kembali = tanggal_kembali;
    this.tenggat_kembali = tenggat_kembali;
    this.status_peminjaman = status_peminjaman;
  }
  private Peminjaman(Parcel in) {
    this.id_peminjaman = in.readInt();
    this.id_user = in.readInt();
    this.id_movie = in.readInt();
//    this.movie=in.readParcelable(MovieResults.ResultsBean);
//    this.movie = in.readParcelable(MovieResults.ResultsBean.class.getClassLoader());
    this.movie=in.readString();
    this.harga_sewa = in.readInt();
    this.metode_pembayaran=in.readString();
    this.tanggal_sewa = in.readString();
    this.tanggal_kembali = in.readString();
    this.tenggat_kembali = in.readString();
    this.status_peminjaman = in.readString();
  }

  public static final Parcelable.Creator<Peminjaman> CREATOR = new
      Parcelable.Creator<Peminjaman>() {
        @Override
        public Peminjaman createFromParcel(Parcel source) {
          return new Peminjaman(source);
        }

        @Override
        public Peminjaman[] newArray(int size) {
          return new Peminjaman[size];
        }
      };


}
