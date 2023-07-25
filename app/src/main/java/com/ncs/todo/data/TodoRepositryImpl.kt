package com.ncs.todo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositryImpl(
    private val dao: TodoDao
):TodoRepository  {
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deletetodo(todo: Todo) {
        dao.deletetodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
       return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}