package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface StepHistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StepHistory stepHistory);


    @Query("SELECT steps FROM stephistory WHERE id_user = :id_user AND step_date = :step_date")
    int getUserStepRequirements(long id_user, String step_date);
}
