package com.example.fitmax.Other;


import com.example.fitmax.Database.PhysicalActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonMethods {
    public static float GetActivityCalories(PhysicalActivity activity, float weight) {
        float kcal = activity.getMet() * 0.0175f * weight;

        // rounds to 2 decimals
        return Math.round(kcal * 100f) / 100.0f;
    }
    public static float GetStepCalories(int steps, float weight) {
        // 1 step = 0.05 METS
        float kcal = steps * 0.0083f * 0.05f * weight;

        // rounds to 2 decimals
        return Math.round(kcal * 100f) / 100.0f;
    }

    public static String getToday(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
