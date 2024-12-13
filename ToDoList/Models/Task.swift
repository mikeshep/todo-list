//
//  Task.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 13/12/24.
//

import Foundation
import SwiftData

enum TaskPriority: String, Codable, CaseIterable {
    case alta = "Alta"
    case media = "Media"
    case baja = "Baja"
}

@Model
class Task: Identifiable {
    var id: UUID
    var title: String
    var dueDate: Date?
    var priority: TaskPriority
    var isCompleted: Bool
    var isScheduled: Bool

    init(
        title: String,
        dueDate: Date? = nil,
        priority: TaskPriority = .media,
        isCompleted: Bool = false,
        isScheduled: Bool = false
    ) {
        self.id = UUID()
        self.title = title
        self.dueDate = dueDate
        self.priority = priority
        self.isCompleted = isCompleted
        self.isScheduled = isScheduled
    }
}

extension Task {
    var isOverdue: Bool {
        if let dueDate = dueDate {
            return dueDate < Date() && !isCompleted
        }
        return false
    }
}
