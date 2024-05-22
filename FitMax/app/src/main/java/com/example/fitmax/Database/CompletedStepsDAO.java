package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompletedStepsDAO {
    @Insert
    void insert(CompletedSteps completedSteps);

    @Query("SELECT * FROM completedsteps WHERE id_user = :id_user")
    List<CompletedSteps> GetCSFromId(long id_user);

    @Query("SELECT * FROM completedsteps WHERE id_user = :id_user AND completion_date = :completion_date")
    List<CompletedSteps> GetCSFromId(long id_user, String completion_date);
}
