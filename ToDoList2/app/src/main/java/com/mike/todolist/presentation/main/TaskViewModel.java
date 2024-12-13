package com.mike.todolist.presentation.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mike.todolist.data.model.Task;
import com.mike.todolist.data.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskViewModel extends ViewModel {

    private final TaskRepository taskRepository;
    private final MutableLiveData<List<Task>> tasksLiveData = new MutableLiveData<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TaskViewModel(Context context) {
        taskRepository = new TaskRepository(context);
        loadTasks();
    }

    public LiveData<List<Task>> getTasksLiveData() {
        return tasksLiveData;
    }

    public void loadTasks() {
        executor.execute(() -> {
            List<Task> tasks = taskRepository.getAllTasks();
            tasksLiveData.postValue(tasks);
        });
    }

    public void addTask(Task task) {
        executor.execute(() -> {
            taskRepository.insert(task);
            loadTasks();
        });
    }

    public void updateTask(Task task) {
        executor.execute(() -> {
            taskRepository.update(task);
            loadTasks();
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskRepository.delete(task);
            loadTasks();
        });
    }
}
