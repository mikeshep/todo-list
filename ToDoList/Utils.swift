//
//  Utils.swift
//  ToDoList
//
//  Created by Miguel Cuponerapp on 13/12/24.
//

import Foundation

// MARK: - Funciones de Formateo

func formattedCurrentDate() -> String {
    let formatter = DateFormatter()
    formatter.dateStyle = .full
    return formatter.string(from: Date())
}

func formattedCurrentTime() -> String {
    let formatter = DateFormatter()
    formatter.timeStyle = .short
    return formatter.string(from: Date())
}

func formattedTime(from date: Date) -> String {
    let formatter = DateFormatter()
    formatter.timeStyle = .short
    return formatter.string(from: date)
}

func formattedDate(_ date: Date) -> String {
    let formatter = DateFormatter()
    formatter.dateStyle = .full
    return formatter.string(from: date)
}
