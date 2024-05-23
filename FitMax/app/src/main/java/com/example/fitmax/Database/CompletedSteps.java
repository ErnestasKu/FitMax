package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id_user",
        childColumns = "id_user",
        onDelete = ForeignKey.CASCADE),
        primaryKeys = {"id_user", "completion_date"})
public class CompletedSteps {

    private long id_completed_steps;

    private int step_count;

    private long id_user;

    @NonNull
    @ColumnInfo(name = "completion_date")
    private String completion_date;

    // methods -------------------------------------------------------------------------------------

    public long getId_completed_steps() {
        return id_completed_steps;
    }

    public void setId_completed_steps(long id_completed_steps) {
        this.id_completed_steps = id_completed_steps;
    }

    public int getStep_count() {
        return step_count;
    }

    public void setStep_count(int step_count) {
        this.step_count = step_count;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    @NonNull
    public String getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(@NonNull String completion_date) {
        this.completion_date = completion_date;
    }
}
