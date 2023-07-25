package com.ncs.todo.todolist

import com.ncs.todo.data.Todo

sealed class TodoListEvent{
    data class DeleteTodo(val todo: Todo):TodoListEvent()
    data class onDoneChange(val todo: Todo, val isDone:Boolean):TodoListEvent()
    object onUndoDeleteClick:TodoListEvent()
    data class onTodoClick(val todo: Todo):TodoListEvent()
    object onAddTodoClick:TodoListEvent()
}
