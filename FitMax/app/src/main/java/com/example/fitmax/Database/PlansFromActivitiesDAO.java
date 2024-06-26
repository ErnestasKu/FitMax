package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlansFromActivitiesDAO {

    @Insert
    void insert(PlansFromActivities plansFromActivities);


    @Query("SELECT COUNT(*) FROM plansfromactivities WHERE id_plan = :id_plan AND weekday = :weekday")
    int getActivityCountOfPlanDay(long id_plan, String weekday);

    @Query("SELECT PhysicalActivity.* " +
            " FROM PhysicalActivity" +
            " JOIN plansfromactivities on physicalactivity.id_activity = plansfromactivities.id_activity" +
            " WHERE plansfromactivities.id_plan = :id_plan" +
            " AND plansfromactivities.weekday = :weekday")
    List<PhysicalActivity> getActivitiesOfDay(long id_plan, String weekday);
}
