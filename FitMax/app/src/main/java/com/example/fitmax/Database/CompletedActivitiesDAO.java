package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompletedActivitiesDAO {
    @Insert
    void insert(CompletedActivities completedActivities);

    @Query("SELECT * FROM completedactivities WHERE id_user = :id_user")
    List<CompletedActivities> GetCAFromId(long id_user);
}
