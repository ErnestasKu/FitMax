package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PlanHistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlanHistory planHistory);

    // gets plan that's closest to given date, but not over
    @Query("SELECT id_plan FROM planhistory" +
            " WHERE id_user = :id_user" +
            " AND plan_date <= :plan_date" +
            " ORDER BY plan_date DESC" +
            " LIMIT 1")
    int getUserPlan(long id_user, String plan_date);
}
