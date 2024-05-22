package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id_user",
                childColumns = "id_user",
                onDelete = ForeignKey.CASCADE)
},
        primaryKeys = {"id_user", "step_date"})
public class StepHistory {

    private long id_user;

    @NonNull
    private String step_date;

    @ColumnInfo(name = "steps")
    private int steps;

    // methods -------------------------------------------------------------------------------------

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    @NonNull
    public String getStep_date() {
        return step_date;
    }

    public void setStep_date(@NonNull String step_date) {
        this.step_date = step_date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
