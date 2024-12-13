//
//  TodoListView.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import SwiftUI

import SwiftUI

struct TodoListView: View {
    @StateObject var viewModel = TodoViewModel()
    @State private var newTodoTitle = ""
    @State private var isAnimating = false  // Estado para la animación
    
    var body: some View {
        NavigationView {
            VStack {
                HStack {
                    TextField("New Todo", text: $newTodoTitle)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    
                    Button(action: {
                        Task {
                            await addNewTodo()
                        }
                    }) {
                        Image(systemName: "plus")
                            .font(.title)
                            .rotationEffect(.degrees(isAnimating ? 90 : 0))
                            .scaleEffect(isAnimating ? 1.5 : 1)
                            .animation(.easeOut(duration: 0.3), value: isAnimating)
                    }
                    .buttonStyle(BorderlessButtonStyle())
                }
                .padding()
                
                List {
                    ForEach(viewModel.todos) { todo in
                        TodoRowView(todo: todo) {
                            viewModel.toggleTodoCompletion(todo: todo)
                        }
                        .transition(.move(edge: .trailing).combined(with: .opacity))
                    }
                    .onDelete { indexSet in
                        Task {
                            await viewModel.deleteTodo(at: indexSet)
                        }
                    }
                }
                .listStyle(PlainListStyle())
            }
            .navigationTitle("Todo List")
            .task {
                await viewModel.loadTodos()
            }
            .alert(item: $viewModel.errorMessage) { error in
                Alert(title: Text("Error"), message: Text(error.message), dismissButton: .default(Text("OK")))
            }
        }
    }

    private func addNewTodo() async {
        let title = newTodoTitle.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !title.isEmpty else { return }
        
        withAnimation {
            Task {
                await viewModel.addTodo(title: title)
                newTodoTitle = ""
            }
        }
    }
}
