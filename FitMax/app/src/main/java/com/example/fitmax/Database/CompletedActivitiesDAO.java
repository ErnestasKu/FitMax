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

    @Query("SELECT COUNT(*) FROM completedactivities" +
            " WHERE id_activity = :id_activity" +
            " AND completion_date = :date" +
            " AND id_user = :id_user")
    boolean checkIfCompleted(Long id_user, Long id_activity, String date);

    @Query("SELECT COUNT(*) FROM completedactivities" +
            " WHERE id_user = :id_user" +
            " AND completion_date = :date")
    int getCompletedCountInDay(Long id_user, String date);
}
