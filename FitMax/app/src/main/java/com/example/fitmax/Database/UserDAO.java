package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user ORDER BY username ASC")
    List<User> getAllUsers();

    @Query("SELECT COUNT(*) FROM User WHERE email = :email_string;")
    boolean checkIfEmailAvailable(String email_string);
}
