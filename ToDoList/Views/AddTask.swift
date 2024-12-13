//
//  AddTask.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 13/12/24.
//

import SwiftUI

struct AddTask: View {
    @Environment(\.modelContext) private var modelContext
    @Environment(\.dismiss) private var dismiss
    
    let task: Task?
    @State private var title: String
    @State private var selectedDate: Date
    @State private var selectedTime: Date
    @State private var selectedOption: TaskSchedule
    @State private var priority: TaskPriority
    
    init(task: Task? = nil) {
        self.task = task
        _title = State(initialValue: task?.title ?? "")
        _selectedDate = State(initialValue: task?.dueDate ?? Date())
        _selectedTime = State(initialValue: task?.dueDate ?? Date())
        _selectedOption = State(initialValue: task?.isScheduled == true ? .scheduled : .anytime)
        _priority = State(initialValue: task?.priority ?? .media)
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 24) {
            
            TextField("Título", text: $title)
                .font(.title)
                .bold()
                .padding(.horizontal)
            
            HStack {
                Image(systemName: "calendar")
                    .foregroundColor(.blue)
                DatePicker("Fecha", selection: $selectedDate, displayedComponents: .date)
                    .datePickerStyle(.compact)
                    .labelsHidden()
            }
            .padding(.horizontal)
            
            HStack {
                Image(systemName: "clock")
                    .foregroundColor(.blue)
                DatePicker("Hora", selection: $selectedTime, displayedComponents: .hourAndMinute)
                    .datePickerStyle(.compact)
                    .labelsHidden()
            }
            .padding(.horizontal)
            
            HStack {
                Text("Prioridad:")
                    .font(.headline)
                Picker("Prioridad", selection: $priority) {
                    ForEach(TaskPriority.allCases, id: \.self) { priority in
                        Text(priority.rawValue.capitalized).tag(priority)
                    }
                }
                .pickerStyle(SegmentedPickerStyle())
            }
            .padding(.horizontal)
            
            Spacer()
        }
        .navigationTitle(task == nil ? "Crear Tarea" : "Editar Tarea")
        .navigationBarTitleDisplayMode(.inline)
        .padding(.top)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: {
                    saveTask()
                }) {
                    Image(systemName: "square.and.arrow.down")
                        .foregroundColor(canSaveTask ? .blue : .gray)
                }
                .disabled(!canSaveTask)
            }
        }
    }
    
    // Computed property para validar si se puede guardar la tarea
    private var canSaveTask: Bool {
        !title.isEmpty
    }
    
    private func saveTask() {
        if let task = task {
            // Actualizar la tarea existente
            task.title = title
            task.dueDate = selectedOption == .scheduled ? selectedDate : nil
            task.priority = priority
            task.isScheduled = selectedOption == .scheduled
        } else {
            // Crear una nueva tarea
            let newTask = Task(
                title: title,
                dueDate: selectedDate,
                priority: priority,
                isCompleted: false,
                isScheduled: true
            )
            modelContext.insert(newTask)
        }
        do {
            try modelContext.save()
        } catch {
            debugPrint(error)
        }
        dismiss()
    }
}
