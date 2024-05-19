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


//    @Query("SELECT PhysicalActivity.id_activity, " +
//            "PhysicalActivity.activity_name, " +
//            "PhysicalActivity.duration, " +
//            "PhysicalActivity.activity_type, " +
//            "PhysicalActivity.met " +
//            "FROM `plan` " +
//            "JOIN PlansFromActivities on `plan`.id_plan = PlansFromActivities.id_plan " +
//            "JOIN PhysicalActivity on PhysicalActivity.id_activity = PlansFromActivities.id_activity " +
//            "JOIN User on user.id_plan = PlansFromActivities.id_plan " +
//            "WHERE User.id_user = :id_user " +
//            "AND PlansFromActivities.weekday = CASE strftime('%w', 'now') " +
//            "            WHEN '0' THEN 'Sunday' " +
//            "            WHEN '1' THEN 'Monday' " +
//            "            WHEN '2' THEN 'Tuesday' " +
//            "            WHEN '3' THEN 'Wednesday' " +
//            "            WHEN '4' THEN 'Thursday' " +
//            "            WHEN '5' THEN 'Friday' " +
//            "            ELSE 'Saturday' " +
//            "       END;")
//    List<PhysicalActivity> GetTodaysActivities(long id_user);


    @Query("SELECT * FROM physicalactivity WHERE id_activity != :id_user")
    List<PhysicalActivity> GetTodaysActivities(long id_user);
}
