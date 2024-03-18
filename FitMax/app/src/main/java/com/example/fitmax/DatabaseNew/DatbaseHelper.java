//package com.example.fitmax.DatabaseNew;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class DatbaseHelper extends SQLiteOpenHelper {
//    private Context context;
//    private static final String DATABASE_NAME = "fitmax_db2";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String TABLE_NAME = "User";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_EMAIL = "email";
//    private static final String COLUMN_USERNAME = "username";
//    private static final String COLUMN_PASSWORD = "password";
//
//
//    public DatbaseHelper(@Nullable Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String query =
//                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
//                        "INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        COLUMN_EMAIL + " TEXT, " +
//                        COLUMN_USERNAME + " TEXT, " +
//                        COLUMN_PASSWORD + " TEXT);";
//        db.execSQL(query);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//}
