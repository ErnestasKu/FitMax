package com.example.fitmax.Other;


import com.example.fitmax.Database.PhysicalActivity;

public class CommonMethods {
    public static float GetActivityCalories(PhysicalActivity activity, float weight) {
        float kcal = activity.getMet() * 0.0175f * weight;

        // rounds to 2 decimals
        return Math.round(kcal * 100) / 100.0f;
    }
}
