package com.example.fitmax.Other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Login;
import com.example.fitmax.MainActivity;
import com.example.fitmax.Questionnaire;
import com.example.fitmax.TabScreen;

public class SessionManager {

//    public  static AppDatabase db = AppActivity.getDatabase();

    public static void storeLoginSession(Context context, long id){
        SharedPreferences sharedPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putLong("user", id);
        editor.apply();
    }

    public static void GoToLogin(Context context) {
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }

    public static void GoToStartingActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void GoToMain(Context context) {
        Intent intent = new Intent(context, TabScreen.class);
        context.startActivity(intent);
    }

    public static long getLoginSession(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sharedPrefs.getLong("user", -1);
    }

    public static void endSession(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove("user");
        editor.apply();
    }
}
