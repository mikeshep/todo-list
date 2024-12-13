package com.mike.todolist.domain.usecase;

import com.mike.todolist.data.model.Task;
import com.mike.todolist.data.repository.TaskRepository;

public class UpdateTaskUseCase {
    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(Task task) {
        repository.update(task);
    }
}
