package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index(value = {"email"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    // methods -------------------------------------------------------------------------------------
//    public User(@NonNull String username, @NonNull String password) {
//        this.username = username;
//        this.password = password;
//    }

    // get id
    public long getId() {
        return id;
    }

    // get username
    @NonNull
    public String getEmail() {
        return email;
    }

    // get username
    @NonNull
    public String getUsername() {
        return username;
    }

    // get password
    @NonNull
    public String getPassword() {
        return password;
    }

    // set id
    public void setId(long id) {
        this.id = id;
    }

    // set username
    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    // set username
    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    // set password
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

}
