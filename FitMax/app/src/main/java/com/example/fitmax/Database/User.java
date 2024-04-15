package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index(value = {"email"}, unique = true)})
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

    @ColumnInfo(name = "id_plan")
    private float id_plan;

    // methods -------------------------------------------------------------------------------------


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

    public float getId_plan() {
        return id_plan;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
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

    public void setId_plan(float id_plan) {
        this.id_plan = id_plan;
    }
}
