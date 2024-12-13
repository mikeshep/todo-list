//
//  TodoRowView.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import SwiftUI

struct TodoRowView: View {
    let todo: Todo
    let onToggle: () -> Void

    var body: some View {
        Button(action: {
            onToggle()
        }) {
            HStack {
                Text(todo.title)
                    .strikethrough(todo.isCompleted, color: .gray)
                    .foregroundColor(todo.isCompleted ? .gray : .black)
                Spacer()
                Image(systemName: todo.isCompleted ? "checkmark.circle.fill" : "circle")
                    .foregroundColor(todo.isCompleted ? .green : .gray)
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 10).stroke(Color.gray))
            .animation(.easeInOut, value: todo.isCompleted)
        }
        .buttonStyle(PlainButtonStyle())
    }
}


