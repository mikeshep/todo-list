package com.mike.todolist.presentation.addtask;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.mike.todolist.data.model.Task;
import com.mike.todolist.data.repository.TaskRepository;
import com.mike.todolist.domain.usecase.AddTaskUseCase;
import com.mike.todolist.domain.usecase.UpdateTaskUseCase;

public class AddTaskViewModel extends AndroidViewModel {
    private final AddTaskUseCase addTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public AddTaskViewModel(Application application) {
        super(application);
        TaskRepository repository = new TaskRepository(application);
        this.addTaskUseCase = new AddTaskUseCase(repository);
        this.updateTaskUseCase = new UpdateTaskUseCase(repository);
    }

    public void saveTask(Task task) {
        addTaskUseCase.execute(task);
    }

    public void updateTask(Task task) {
        updateTaskUseCase.execute(task);
    }
}
