//
//  Todo.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import Foundation
import SwiftData

@Model
class Todo: Identifiable {//Identifiable
    @Attribute(.unique) var id: UUID
    var title: String
    var isCompleted: Bool

    init(id: UUID = UUID(), title: String, isCompleted: Bool = false) {
        self.id = id
        self.title = title
        self.isCompleted = isCompleted
    }
}

extension Todo {
    func toDTO() -> TodoDTO {
        TodoDTO(title: title, isCompleted: isCompleted)
    }

    func toggleCompletion() {
            isCompleted.toggle()
        }
}

struct TodoDTO: Codable {
    let title: String
    let isCompleted: Bool
}

extension TodoDTO {
    func toDomain() -> Todo {
        Todo(title: title, isCompleted: isCompleted)
    }
}
