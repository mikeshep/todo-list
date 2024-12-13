//
//  TaskList.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 12/12/24.
//

import SwiftUI
import SwiftData

struct TaskList: View {
    @State private var isEditing = false
    @State private var selectedTaskToAdd = false
    @State private var searchText = ""
    @State private var isSearching = false
    @State private var selectedPriority: TaskPriority? = nil
    
    @Environment(\.modelContext) private var modelContext
    @Query private var tasks: [Task]
    
    var body: some View {
        NavigationStack {
            ZStack {
                VStack {
                    if isSearching {
                        searchBar()
                    }
                    taskListView()
                }
                floatingAddButton()
            }
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                toolbarContent()
            }
            .environment(\.editMode, .constant(isEditing ? .active : .inactive))
            .navigationDestination(isPresented: $selectedTaskToAdd) {
                AddTask()
            }
        }
    }
}

// MARK: - Subviews
extension TaskList {
    
    private func taskListView() -> some View {
        List {
            ForEach(groupTasksByDate(), id: \.0) { (date, tasksForDate) in
                Section(header: Text("\(formattedDate(date))")) {
                    ForEach(filteredTasks(tasks: tasksForDate)) { task in
                        NavigationLink(destination: AddTask(task: task)) {
                            TaskRow(task: task, isChecked: task.isCompleted) {
                                toggleTaskCompletion(task)
                            }
                            .foregroundColor(task.isOverdue ? .red : .primary)
                            .animation(.default, value: task.isCompleted)
                        }
                    }
                    .onDelete(perform: deleteTask)
                }
            }
            
            if tasks.contains(where: { $0.isCompleted }) {
                overdueTasksSection()
            }
        }
        .buttonStyle(.plain)
        .listStyle(InsetGroupedListStyle())
    }
    
    private func overdueTasksSection() -> some View {
        Section(header: Text("Vencidas")) {
            ForEach(filteredTasks(tasks: tasks.filter { $0.isCompleted })) { task in
                NavigationLink(destination: AddTask(task: task)) {
                    TaskRow(task: task, isChecked: task.isCompleted) {
                        toggleTaskCompletion(task)
                    }
                    .foregroundColor(.red)
                    .animation(.default, value: task.isCompleted)
                }
            }
            .onDelete(perform: deleteTask)
        }
    }
    
    private func searchBar() -> some View {
        HStack {
            TextField("Buscar tareas...", text: $searchText)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .padding(.horizontal)
            
            Button(action: {
                withAnimation {
                    isSearching = false
                    searchText = ""
                    selectedPriority = nil
                }
            }) {
                Text("Cancelar")
                    .foregroundColor(.blue)
            }
            .padding(.trailing)
        }
        .padding(.vertical, 8)
    }
    
    private func floatingAddButton() -> some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                Button(action: {
                    selectedTaskToAdd = true
                }) {
                    Image(systemName: "plus")
                        .resizable()
                        .frame(width: 24, height: 24)
                        .foregroundColor(.white)
                        .padding()
                        .background(Color.blue)
                        .clipShape(Circle())
                        .shadow(radius: 4)
                }
                .frame(width: 50, height: 50)
                .padding(.trailing, 45)
                .padding(.bottom, 45)
            }
        }
    }
}

// MARK: - Toolbar Content
extension TaskList {
    @ToolbarContentBuilder
    private func toolbarContent() -> some ToolbarContent {
        ToolbarItem(placement: .navigationBarLeading) {
            Button(isEditing ? "Listo" : "Editar") {
                withAnimation {
                    isEditing.toggle()
                }
            }
        }
        
        ToolbarItem(placement: .principal) {
            if !isSearching {
                Text("Tareas por hacer")
                    .font(.headline)
            }
        }
        
        ToolbarItemGroup(placement: .navigationBarTrailing) {
            Button(action: {
                withAnimation {
                    isSearching = true
                }
            }) {
                Image(systemName: "magnifyingglass")
            }
            
            Menu {
                Button("Todas") { selectedPriority = nil }
                Divider()
                Button("Alta") { selectedPriority = .alta }
                Button("Media") { selectedPriority = .media }
                Button("Baja") { selectedPriority = .baja }
            } label: {
                Image(systemName: "line.horizontal.3.decrease.circle")
            }
        }
    }
}

// MARK: - Funciones Auxiliares
extension TaskList {
    private func toggleTaskCompletion(_ task: Task) {
        withAnimation {
            task.isCompleted.toggle()
        }
    }
    
    private func deleteTask(at offsets: IndexSet) {
        for index in offsets {
            let task = tasks[index]
            modelContext.delete(task)
        }
    }
    
    private func groupTasksByDate() -> [(Date, [Task])] {
        let scheduledTasks = tasks.filter { $0.isScheduled && $0.dueDate != nil && !$0.isCompleted }
        
        let groupedDictionary = Dictionary(grouping: scheduledTasks) { task in
            Calendar.current.startOfDay(for: task.dueDate!)
        }
        
        return groupedDictionary.sorted { $0.key < $1.key }
    }
    
    private func filteredTasks(tasks: [Task]) -> [Task] {
        var filtered = tasks
        
        if !searchText.isEmpty {
            filtered = filtered.filter { $0.title.localizedCaseInsensitiveContains(searchText) }
        }
        
        if let priority = selectedPriority {
            filtered = filtered.filter { $0.priority == priority }
        }
        
        return filtered
    }
    
    private func formattedDate(_ date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateStyle = .full
        return formatter.string(from: date)
    }
}
