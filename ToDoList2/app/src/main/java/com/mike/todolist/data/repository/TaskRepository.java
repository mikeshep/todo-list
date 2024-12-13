package com.mike.todolist.data.repository;

import android.content.Context;
import com.mike.todolist.data.database.TaskDatabase;
import com.mike.todolist.data.model.Task;
import java.util.List;

public class TaskRepository {
    private final TaskDatabase taskDatabase;

    public TaskRepository(Context context) {
        taskDatabase = TaskDatabase.getInstance(context);
    }

    public void insert(Task task) {
        new Thread(() -> taskDatabase.taskDao().insert(task)).start();
    }

    public void update(Task task) {
        new Thread(() -> taskDatabase.taskDao().update(task)).start();
    }

    public void delete(Task task) {
        new Thread(() -> taskDatabase.taskDao().delete(task)).start();
    }

    public List<Task> getAllTasks() {
        return taskDatabase.taskDao().getAllTasks();
    }
}
