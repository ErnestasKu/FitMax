package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;


@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id_user",
        childColumns = "id_user",
        onDelete = ForeignKey.CASCADE))

public class LoginDate {
    @PrimaryKey(autoGenerate = true)
    private long id_login;

    @NonNull
    private long id_user;

    @NonNull
    @ColumnInfo(name = "login_date")
    private String login_date;

    public long getId_login() {
        return id_login;
    }

    public long getId_user() {
        return id_user;
    }

    @NonNull
    public String getLogin_date() {
        return login_date;
    }

    public void setId_login(long id_login) {
        this.id_login = id_login;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setLogin_date(@NonNull String login_date) {
        this.login_date = login_date;
    }
}
