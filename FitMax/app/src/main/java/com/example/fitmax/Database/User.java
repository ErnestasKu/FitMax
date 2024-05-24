package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.fitmax.Other.CommonMethods;

import java.time.LocalDate;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id_user;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "weight")
    private float weight;

    @ColumnInfo(name = "creation_date")
    private String creation_date;


    // methods -------------------------------------------------------------------------------------


    public User() {
        creation_date = CommonMethods.getToday();
    }

    public long getId_user() {
        return id_user;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public float getWeight() {
        return weight;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
