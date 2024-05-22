package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Plan.class,
                parentColumns = "id_plan",
                childColumns = "id_plan",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "id_user",
                childColumns = "id_user",
                onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"id_user", "plan_date"})
public class PlanHistory {

    private long id_user;

    @NonNull
    private String plan_date;

    @NonNull
    @ColumnInfo(name = "id_plan")
    private long id_plan;

    // methods -------------------------------------------------------------------------------------

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    @NonNull
    public String getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(@NonNull String plan_date) {
        this.plan_date = plan_date;
    }

    public long getId_plan() {
        return id_plan;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }
}
