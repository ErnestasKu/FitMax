package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompletedStepsDAO {
    @Insert
    void insert(CompletedSteps completedSteps);

    @Query("SELECT * FROM completedsteps WHERE id_user = :id_user")
    List<CompletedSteps> getCSFromId(long id_user);

    @Query("SELECT step_count FROM completedsteps WHERE id_user = :id_user AND completion_date = :completion_date")
    int getUserSteps(long id_user, String completion_date);

    @Query("UPDATE completedsteps SET step_count = step_count + :step_count WHERE id_user = :id_user AND completion_date = :completion_date")
    void addSteps(long id_user, String completion_date, int step_count);

    @Query("SELECT COUNT(*) FROM completedsteps WHERE id_user = :id_user AND completion_date = :completion_date")
    int userEntriesAdded(long id_user, String completion_date);

    @Query("DELETE FROM completedsteps WHERE id_user = :id_user AND completion_date = :completion_date")
    void deleteSteps(long id_user, String completion_date);

    @Query("DELETE FROM completedsteps WHERE id_user = :id_user")
    void deleteAll(long id_user);
}
