package com.mike.todolist.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mike.todolist.dao.TaskDao;
import com.mike.todolist.data.model.Task;
import com.mike.todolist.utils.Converters;

@Database(entities = {Task.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {

    private static volatile TaskDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "task_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
