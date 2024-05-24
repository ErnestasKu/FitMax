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

    @Query("SELECT PhysicalActivity.* FROM completedactivities" +
            " JOIN PhysicalActivity on PhysicalActivity.id_activity = completedactivities.id_activity " +
            " WHERE id_user = :id_user" +
            " AND completion_date = :date")
    List<PhysicalActivity> getCompletedInDay(Long id_user, String date);

    @Query("SELECT PhysicalActivity.* FROM completedactivities" +
            " JOIN PhysicalActivity on PhysicalActivity.id_activity = completedactivities.id_activity " +
            " WHERE id_user = :id_user" +
            " AND completedactivities.completion_date BETWEEN" +
            " :startDate AND :endDate")
    List<PhysicalActivity> getCompletedInTimespan(Long id_user, String startDate, String endDate);

    @Query("DELETE FROM completedactivities WHERE id_user = :id_user AND completion_date = :completion_date")
    void deleteActivities(long id_user, String completion_date);

    @Query("DELETE FROM completedactivities WHERE id_user = :id_user")
    void deleteAll(long id_user);

}
