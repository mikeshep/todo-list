//
//  TodoService.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import Foundation

class TodoService {
    static let shared = TodoService()
    private let todosURL = URL(string: "https://example.com/todos")!
    
    func fetchTodos() async throws -> [Todo] {
        let (data, _) = try await URLSession.shared.data(from: todosURL)
        return try JSONDecoder().decode([TodoDTO].self, from: data).map { $0.toDomain() }
    }
    
    func addTodo(_ todo: Todo) async throws {
        var request = URLRequest(url: todosURL)
        request.httpMethod = "POST"
        request.httpBody = try JSONEncoder().encode(todo.toDTO())
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        _ = try await URLSession.shared.data(for: request)
    }

    func deleteTodo(id: UUID) async throws {
        let url = todosURL.appendingPathComponent(id.uuidString)
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        _ = try await URLSession.shared.data(for: request)
    }
}

