package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDateDAO {
    @Insert
    void insert(LoginDate loginDate);

    @Query("SELECT login_date FROM LoginDate WHERE id_user = :id_user")
    List<String> getAllLoginsById(Long id_user);
}
