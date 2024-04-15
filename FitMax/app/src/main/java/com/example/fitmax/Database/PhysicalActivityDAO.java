package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhysicalActivityDAO {

    @Insert
    void insert(PhysicalActivity physicalActivity);

    @Query("SELECT * FROM PhysicalActivity")
    List<PhysicalActivity> getAll();
}
