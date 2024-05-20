package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDAO {

    @Insert
    void insert(Plan plan);

    @Query("SELECT * FROM `plan`")
    List<Plan> getAll();
}
