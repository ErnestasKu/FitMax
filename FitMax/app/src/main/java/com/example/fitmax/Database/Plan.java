package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plan {

    @PrimaryKey(autoGenerate = true)
    private long id_plan;

    @NonNull
    @ColumnInfo(name = "plan_name")
    private String plan_name;

    public long getId_plan() {
        return id_plan;
    }

    @NonNull
    public String getPlan_name() {
        return plan_name;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }

    public void setPlan_name(@NonNull String plan_name) {
        this.plan_name = plan_name;
    }
}
