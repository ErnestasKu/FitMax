package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface PlansFromActivitiesDAO {

    @Insert
    void insert(PlansFromActivities plansFromActivities);
}
