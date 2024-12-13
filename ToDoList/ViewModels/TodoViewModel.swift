//
//  TodoViewModel.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import Foundation
import SwiftUI

@MainActor
class TodoViewModel: ObservableObject {
    @Published var todos: [Todo] = []
    @Published var errorMessage: ErrorMessage?

    let service = TodoService.shared

    func loadTodos() async {
        do {
            todos = try await service.fetchTodos()
        } catch {
            errorMessage = ErrorMessage(message: "Failed to load todos.")
        }
    }

    func addTodo(title: String) async {
        let newTodo = Todo(title: title)
        do {
            try await service.addTodo(newTodo)
            todos.append(newTodo)
        } catch {
            errorMessage = ErrorMessage(message: "Failed to add todo.")
        }
    }

    func deleteTodo(at indexSet: IndexSet) async {
        guard let index = indexSet.first else { return }
        let todo = todos[index]
        do {
            try await service.deleteTodo(id: todo.id)
            todos.remove(atOffsets: indexSet)
        } catch {
            errorMessage = ErrorMessage(message: "Failed to delete todo.")
        }
    }

    func toggleTodoCompletion(todo: Todo) {
        if let index = todos.firstIndex(where: { $0.id == todo.id }) {
            todos[index].toggleCompletion()
        }
    }
}
