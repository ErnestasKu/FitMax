package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PlansFromActivitiesDAO {

    @Insert
    void insert(PlansFromActivities plansFromActivities);


    @Query("SELECT COUNT(*) FROM plansfromactivities WHERE id_plan = :id_plan AND weekday = :weekday")
    int getActivityCountOfPlanDay(long id_plan, String weekday);
}
