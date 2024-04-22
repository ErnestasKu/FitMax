package com.example.fitmax.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        User.class,
        LoginDate.class,
        PhysicalActivity.class,
        Plan.class,
        CompletedActivities.class,
        PlansFromActivities.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();

    public abstract LoginDateDAO loginDateDAO();

    public abstract PhysicalActivityDAO physicalActivityDAO();

    public abstract PlanDAO planDAO();

    public abstract PlansFromActivitiesDAO plansFromActivitiesDAO();
    public abstract CompletedActivitiesDAO completedActivitiesDAO();
}
