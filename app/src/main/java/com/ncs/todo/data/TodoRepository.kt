package com.ncs.todo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)
    suspend fun deletetodo(todo: Todo)
    suspend fun getTodoById(id : Int):Todo?
    fun getTodos(): Flow<List<Todo>>
}