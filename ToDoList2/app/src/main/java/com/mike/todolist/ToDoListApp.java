package com.mike.todolist;

import android.app.Application;

import com.mike.todolist.data.database.TaskDatabase;

public class ToDoListApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TaskDatabase.getInstance(this);
    }
}
