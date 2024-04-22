package com.example.fitmax.Database;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;


public class AppActivity extends Application {
    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this, AppDatabase.class, "fitmax_db")
                .allowMainThreadQueries().build();

        try {
            SupportSQLiteDatabase sqLiteDatabase = db.getOpenHelper().getWritableDatabase();
            String[] queries = BaseData.base_data.split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    sqLiteDatabase.execSQL(query);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static AppDatabase getDatabase() {
        return db;
    }
}
