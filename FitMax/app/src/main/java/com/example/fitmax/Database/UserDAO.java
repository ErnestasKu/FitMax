package com.example.fitmax.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user ORDER BY username ASC")
    List<User> getAllUsers();

    @Query("SELECT EXISTS (SELECT 1 FROM user WHERE email = :email_string)")
    boolean checkIfEmailAvailable(String email_string);

    @Query("SELECT id_user FROM user WHERE email = :email_string AND password = :password_string")
    long getIdByLogin(String email_string, String password_string);

    @Query("SELECT username FROM user WHERE id_user = :id_user")
    String getUsernameById(Long id_user);

    @Query("SELECT CASE WHEN weight > 0 THEN 1 ELSE 0 END FROM user WHERE id_user = :id_user")
    boolean checkIfQuestionnaireComplete(long id_user);

    @Query("UPDATE user SET weight = :weight, id_plan = :id_plan WHERE id_user = :id_user")
    void finishQuestionnaire(long id_user, float weight, long id_plan);

    @Query("SELECT PhysicalActivity.* " +
            "FROM `plan` " +
            "JOIN PlansFromActivities on `plan`.id_plan = PlansFromActivities.id_plan " +
            "JOIN PhysicalActivity on PhysicalActivity.id_activity = PlansFromActivities.id_activity " +
            "JOIN User on user.id_plan = PlansFromActivities.id_plan " +
            "WHERE User.id_user = :id_user " +
            "AND PlansFromActivities.weekday = :weekday")
    List<PhysicalActivity> getActivitiesOfDay(long id_user, String weekday);

    @Query("SELECT user.* FROM user WHERE id_user = :id_user")
    User getUser(long id_user);

    @Query("SELECT id_plan FROM user WHERE id_user = :id_user")
    long getUserPlan(long id_user);
}
