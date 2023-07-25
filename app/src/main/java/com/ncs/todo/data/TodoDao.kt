package com.ncs.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)
    @Delete
    suspend fun deletetodo(todo: Todo)
    @Query("SELECT * FROM TODO WHERE id=:id")
    suspend fun getTodoById(id : Int):Todo?
    @Query("SELECT * FROM TODO")
    fun getTodos(): Flow<List<Todo>>
}