package com.mike.todolist.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "tasks")
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    private Date dueDate;

    @NonNull
    private String priority; // "Alta", "Media", "Baja"

    private boolean isCompleted;

    private boolean isScheduled;

    // Constructor
    public Task(@NonNull String title, Date dueDate, @NonNull String priority, boolean isCompleted, boolean isScheduled) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.isScheduled = isScheduled;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @NonNull
    public String getPriority() {
        return priority;
    }

    public void setPriority(@NonNull String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }
}
