package com.mike.todolist.presentation.addtask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mike.todolist.R;
import com.mike.todolist.data.model.Task;
import com.mike.todolist.utils.DateTimeUtils;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private Spinner spinnerPriority;
    private Button buttonSave;
    private ImageButton buttonSelectDate, buttonSelectTime;
    private TextView textViewSelectedDate, textViewSelectedTime;

    private Calendar selectedDateTime = Calendar.getInstance();
    private AddTaskViewModel viewModel;
    private Task taskToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Inicializar vistas
        editTextTitle = findViewById(R.id.editTextTitle);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectTime = findViewById(R.id.buttonSelectTime);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        textViewSelectedTime = findViewById(R.id.textViewSelectedTime);

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);

        // Verificar si se está editando una tarea existente
        taskToEdit = (Task) getIntent().getSerializableExtra("task");

        if (taskToEdit != null) {
            editTextTitle.setText(taskToEdit.getTitle());
            setSpinnerPriority(taskToEdit.getPriority());

            if (taskToEdit.getDueDate() != null) {
                selectedDateTime.setTime(taskToEdit.getDueDate());
            }

            buttonSave.setText("Actualizar");
        }

        updateDateTimeLabels();

        // Listeners para selección de fecha y hora
        buttonSelectDate.setOnClickListener(v -> showDatePicker());
        buttonSelectTime.setOnClickListener(v -> showTimePicker());

        buttonSave.setOnClickListener(v -> {
            if (taskToEdit == null) {
                saveTask();
            } else {
                updateTask();
            }
        });
    }

    private void showDatePicker() {
        new DatePickerDialog(this, (view, year, month, day) -> {
            selectedDateTime.set(Calendar.YEAR, year);
            selectedDateTime.set(Calendar.MONTH, month);
            selectedDateTime.set(Calendar.DAY_OF_MONTH, day);
            updateDateTimeLabels();
        }, selectedDateTime.get(Calendar.YEAR), selectedDateTime.get(Calendar.MONTH), selectedDateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker() {
        new TimePickerDialog(this, (view, hour, minute) -> {
            selectedDateTime.set(Calendar.HOUR_OF_DAY, hour);
            selectedDateTime.set(Calendar.MINUTE, minute);
            updateDateTimeLabels();
        }, selectedDateTime.get(Calendar.HOUR_OF_DAY), selectedDateTime.get(Calendar.MINUTE), true).show();
    }

    private void updateDateTimeLabels() {
        textViewSelectedDate.setText(DateTimeUtils.formatDate(selectedDateTime));
        textViewSelectedTime.setText(DateTimeUtils.formatTime(selectedDateTime));
    }

    private void setSpinnerPriority(String priority) {
        String[] priorities = getResources().getStringArray(R.array.priorities);
        for (int i = 0; i < priorities.length; i++) {
            if (priorities[i].equals(priority)) {
                spinnerPriority.setSelection(i);
                break;
            }
        }
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString().trim();
        String priority = spinnerPriority.getSelectedItem().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        Task newTask = new Task(title, selectedDateTime.getTime(), priority, false, true);
        viewModel.saveTask(newTask);
        setResult(RESULT_OK);
        finish();
    }

    private void updateTask() {
        String title = editTextTitle.getText().toString().trim();
        String priority = spinnerPriority.getSelectedItem().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        taskToEdit.setTitle(title);
        taskToEdit.setDueDate(selectedDateTime.getTime());
        taskToEdit.setPriority(priority);

        viewModel.updateTask(taskToEdit);
        setResult(RESULT_OK);
        finish();
    }
}
