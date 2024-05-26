package com.example.tapetrove.Database;

public class Users {
  private String key;
  private String userId, email, password, username, address, telephone;

  public Users() {
  }
  public Users(String userId, String email, String password, String username, String address, String telephone) {
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.username = username;
    this.address = address;
    this.telephone = telephone;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
}
