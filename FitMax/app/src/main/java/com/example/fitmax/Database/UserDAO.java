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

    @Query("UPDATE user SET weight = :weight WHERE id_user = :id_user")
    void updateUserWeight(long id_user, float weight);

    @Query("SELECT user.* FROM user WHERE id_user = :id_user")
    User getUser(long id_user);

    @Query("DELETE FROM user WHERE id_user = :id_user")
    int deleteAll(long id_user);
}
