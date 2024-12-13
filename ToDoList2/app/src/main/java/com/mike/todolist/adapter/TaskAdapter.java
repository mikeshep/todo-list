package com.mike.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mike.todolist.presentation.addtask.AddTaskActivity;
import com.mike.todolist.presentation.main.MainActivity;
import com.mike.todolist.R;
import com.mike.todolist.data.database.TaskDatabase;
import com.mike.todolist.data.model.Task;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);

        // Aplicar animación
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation));

        // Editar tarea al hacer clic
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddTaskActivity.class);
            intent.putExtra("task", task);
            context.startActivity(intent);
        });

        // Eliminar tarea al mantener presionado
        holder.itemView.setOnLongClickListener(v -> {
            new Thread(() -> {
                TaskDatabase.getInstance(context).taskDao().delete(task);
                tasks.remove(position);
                ((MainActivity) context).runOnUiThread(() -> notifyDataSetChanged());
            }).start();
            return true;
        });
    }

    public void updateTasks(List<Task> newTasks) {
        this.tasks = newTasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView dueDate;
        private final TextView priority;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_title);
            dueDate = itemView.findViewById(R.id.task_due_date);
            priority = itemView.findViewById(R.id.task_priority);
        }

        void bind(Task task) {
            title.setText(task.getTitle());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dueDate.setText(task.getDueDate() != null ? dateFormat.format(task.getDueDate()) : "No date");
            priority.setText(task.getPriority());
        }
    }
}
