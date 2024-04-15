package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

enum ActivityType {
    Core,
    Holistic,
    Lower,
    Upper
}

@Entity
public class PhysicalActivity {

    @PrimaryKey(autoGenerate = true)
    private long id_activity;

    @NonNull
    @ColumnInfo(name = "activity_name")
    private String activity_name;

    @NonNull
    @ColumnInfo(name = "duration")
    private String duration;

    @NonNull
    @ColumnInfo(name = "activity_type")
    private ActivityType type;

    @NonNull
    @ColumnInfo(name = "met")
    private Float met;

    // methods -------------------------------------------------------------------------------------
    public long getId_activity() {
        return id_activity;
    }

    @NonNull
    public String getActivity_name() {
        return activity_name;
    }

    @NonNull
    public String getDuration() {
        return duration;
    }

    @NonNull
    public ActivityType getType() {
        return type;
    }

    @NonNull
    public Float getMet() {
        return met;
    }

    public void setId_activity(long id_activity) {
        this.id_activity = id_activity;
    }

    public void setActivity_name(@NonNull String activity_name) {
        this.activity_name = activity_name;
    }

    public void setDuration(@NonNull String duration) {
        this.duration = duration;
    }

    public void setType(@NonNull ActivityType type) {
        this.type = type;
    }

    public void setMet(@NonNull Float met) {
        this.met = met;
    }
}
