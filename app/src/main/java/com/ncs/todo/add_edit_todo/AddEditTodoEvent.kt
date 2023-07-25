package com.ncs.todo.add_edit_todo

sealed class AddEditTodoEvent{
    data class onTitleChnage(val title:String):AddEditTodoEvent()
    data class onDescriptionChange(val description:String):AddEditTodoEvent()
    object onSaveTodoClick:AddEditTodoEvent()
}
