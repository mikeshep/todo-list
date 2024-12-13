package com.mike.todolist.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mike.todolist.data.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY dueDate ASC")
    List<Task> getCompletedTasks();

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY dueDate ASC")
    List<Task> getPendingTasks();
}
