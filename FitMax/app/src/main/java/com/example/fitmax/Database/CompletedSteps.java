package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id_user",
        childColumns = "id_user",
        onDelete = ForeignKey.CASCADE))
public class CompletedSteps {

    @PrimaryKey(autoGenerate = true)
    private long id_completed_steps;

    private int completed_steps;

    private long id_user;

    @NonNull
    @ColumnInfo(name = "completion_date")
    private String completion_date;

    // methods -------------------------------------------------------------------------------------

    public long getId_completed_steps() {
        return id_completed_steps;
    }

    public int getCompleted_steps() {
        return completed_steps;
    }

    public long getId_user() {
        return id_user;
    }

    @NonNull
    public String getCompletion_date() {
        return completion_date;
    }

    public void setId_completed_steps(long id_completed_steps) {
        this.id_completed_steps = id_completed_steps;
    }

    public void setCompleted_steps(int completed_steps) {
        this.completed_steps = completed_steps;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setCompletion_date(@NonNull String completion_date) {
        this.completion_date = completion_date;
    }
}
