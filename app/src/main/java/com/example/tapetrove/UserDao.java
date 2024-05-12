package com.example.tapetrove;

import androidx.room.Dao;
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
    void updateUser (User user);

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User signInWithEmail(String email, String password);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User signInWithUsername(String username, String password);

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM User WHERE username = :username")
    User getuser(String username);

    @Query("SELECT EXISTS(SELECT 1 FROM User WHERE email = :email)")
    boolean checkEmail(String email);
}