package com.example.tapetrove.profile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

  @ColumnInfo(name = "userId")
  @PrimaryKey(autoGenerate = true)
  int userId;

  @ColumnInfo(name = "name")
  String username;

  @ColumnInfo(name = "email")
  String email;

  @ColumnInfo(name = "password")
  String password;

  @ColumnInfo(name = "telephone")
  String telephone;

  @ColumnInfo(name = "address")
  String address;

  public User(String username, String email,
              String password, String telephone, String address) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.telephone = telephone;
    this.address = address;
    this.userId = 0;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
