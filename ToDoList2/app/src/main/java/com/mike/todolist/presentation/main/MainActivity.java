package com.mike.todolist.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.todolist.R;
import com.mike.todolist.adapter.TaskAdapter;
import com.mike.todolist.data.model.Task;
import com.mike.todolist.presentation.addtask.AddTaskActivity;
import com.mike.todolist.presentation.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private Button addButton;
    private TaskViewModel taskViewModel;
    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.button_add_task);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador vacío
        taskAdapter = new TaskAdapter(MainActivity.this, taskList);
        recyclerView.setAdapter(taskAdapter);

        // Inicializar el ViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Observar cambios en los datos
        taskViewModel.getTasksLiveData().observe(this, tasks -> {
            taskList = tasks;
            taskAdapter.updateTasks(tasks);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            taskViewModel.loadTasks();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterTasks(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTasks(newText);
                return true;
            }
        });

        searchView.setOnCloseListener(() -> {
            taskViewModel.loadTasks();
            return false;
        });

        return true;
    }

    private void filterTasks(String query) {
        List<Task> filteredList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(task);
            }
        }
        taskAdapter.updateTasks(filteredList);
    }
}
