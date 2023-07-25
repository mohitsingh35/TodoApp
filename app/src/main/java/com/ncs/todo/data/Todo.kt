package com.ncs.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title:String,
    val description:String,
    val isComplted:Boolean=false,
    @PrimaryKey val id: Int? =null
)
