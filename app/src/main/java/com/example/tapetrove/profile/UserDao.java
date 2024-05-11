package com.example.tapetrove.profile;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


@Dao
public interface UserDao {

  @Query("SELECT * FROM User")
  List<User> getAllUser();

  @Insert
  void insertUser(User user);

  @Update
  void updateUser(User user);

  @Delete
  void deleteUser(User user);

  @Query("SELECT * FROM User WHERE name ==:name")
  public User getUserByName(String name);
}
