//
//  TaskRow.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 13/12/24.
//

import SwiftUI

struct TaskRow: View {
    let task: Task
    let isChecked: Bool
    let onCheckToggle: () -> Void
    
    var body: some View {
        HStack {
            Button(action: onCheckToggle) {
                Image(systemName: isChecked ? "checkmark.circle.fill" : "circle")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .foregroundColor(task.isCompleted ? .green : .gray)
            }
            .padding(.leading)
            .buttonStyle(.plain)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(task.title)
                    .font(.headline)
                
                if let dueDate = task.dueDate {
                    Text(formattedTime(from: dueDate))
                        .font(.subheadline)
                }
                
                // Mostrar la prioridad
                Text("\(task.priority.rawValue.capitalized)")
                    .font(.subheadline)
                    .foregroundColor(priorityColor(for: task.priority))
            }
            Spacer()
        }
        .padding(.vertical, 8)
    }
    
    // Función para obtener el color según la prioridad
    private func priorityColor(for priority: TaskPriority) -> Color {
        switch priority {
        case .alta:
            return .red
        case .media:
            return .yellow
        case .baja:
            return .green
        }
    }
}
