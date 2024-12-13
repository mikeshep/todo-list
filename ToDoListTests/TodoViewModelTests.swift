//
//  TodoViewModelTests.swift
//  ToDoListTests
//
//  Created by Miguel Cuponerapp on 10/12/24.
//

import XCTest
@testable import ToDoList

@MainActor
class TodoViewModelTests: XCTestCase {
    var viewModel: TodoViewModel!

    override func setUp() {
        super.setUp()
        viewModel = TodoViewModel()
    }

    func testAddTodo() async {
        await viewModel.addTodo(title: "Test Todo")
        XCTAssertEqual(viewModel.todos.count, 1)
        XCTAssertEqual(viewModel.todos.first?.title, "Test Todo")
    }

    func testDeleteTodo() async {
        await viewModel.addTodo(title: "Test Todo")
        XCTAssertEqual(viewModel.todos.count, 1)

        let indexSet = IndexSet(integer: 0)
        await viewModel.deleteTodo(at: indexSet)
        XCTAssertEqual(viewModel.todos.count, 0)
    }
}

