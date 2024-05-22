package com.example.fitmax.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = PhysicalActivity.class,
                parentColumns = "id_activity",
                childColumns = "id_activity",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "id_user",
                childColumns = "id_user",
                onDelete = ForeignKey.CASCADE)},

        indices = {
                @Index(value = {"id_activity"}),
                @Index(value = {"id_user"})
        }
)
public class CompletedActivities {

    @PrimaryKey(autoGenerate = true)
    private long id_completed_activity;

    private long id_activity;

    private long id_user;

    @NonNull
    @ColumnInfo(name = "completion_date")
    private String completion_date;

    // methods -------------------------------------------------------------------------------------

    public long getId_completed_activity() {
        return id_completed_activity;
    }

    public void setId_completed_activity(long id_completed_activity) {
        this.id_completed_activity = id_completed_activity;
    }

    public long getId_activity() {
        return id_activity;
    }

    public void setId_activity(long id_activity) {
        this.id_activity = id_activity;
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
