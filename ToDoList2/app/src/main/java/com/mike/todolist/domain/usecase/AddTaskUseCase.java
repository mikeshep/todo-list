package com.mike.todolist.domain.usecase;

import com.mike.todolist.data.model.Task;
import com.mike.todolist.data.repository.TaskRepository;

public class AddTaskUseCase {
    private final TaskRepository repository;

    public AddTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(Task task) {
        repository.insert(task);
    }
}
