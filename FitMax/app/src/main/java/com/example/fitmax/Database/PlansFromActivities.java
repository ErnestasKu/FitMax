package com.example.fitmax.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = PhysicalActivity.class,
                parentColumns = "id_activity",
                childColumns = "id_activity",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Plan.class,
                parentColumns = "id_plan",
                childColumns = "id_plan",
                onDelete = ForeignKey.CASCADE)},

        indices = {
                @Index(value = {"id_activity"}),
                @Index(value = {"id_plan"})
        }
)

public class PlansFromActivities {

    @PrimaryKey(autoGenerate = true)
    private long id_pfa;

    private long id_plan;

    private long id_activity;

    @ColumnInfo(name = "weekday")
    private String weekday;

    // methods -------------------------------------------------------------------------------------


    public void setId_pfa(long id_pfa) {
        this.id_pfa = id_pfa;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }

    public void setId_activity(long id_activity) {
        this.id_activity = id_activity;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public long getId_pfa() {
        return id_pfa;
    }

    public long getId_plan() {
        return id_plan;
    }

    public long getId_activity() {
        return id_activity;
    }

    public String getWeekday() {
        return weekday;
    }
}
