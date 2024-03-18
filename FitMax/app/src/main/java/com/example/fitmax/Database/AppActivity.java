package com.example.fitmax.Database;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;


public class AppActivity extends Application {
    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this, AppDatabase.class, "fitmax_db")
                .allowMainThreadQueries().build();
    }

    public static AppDatabase getDatabase() {
        return db;
    }
}
