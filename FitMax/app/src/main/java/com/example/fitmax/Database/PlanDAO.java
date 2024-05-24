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

    @Query("SELECT `Plan`.* FROM `Plan` WHERE id_plan = :id_plan ORDER BY id_plan ASC")
    Plan getPlan(long id_plan);
}
