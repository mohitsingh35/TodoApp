package com.ncs.todo.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncs.todo.Util.Routes
import com.ncs.todo.Util.UiEvent
import com.ncs.todo.data.Todo
import com.ncs.todo.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
):ViewModel() {

    val todos=repository.getTodos()
    private val _uiEvent = Channel<UiEvent> ()
    val uiEvent=_uiEvent.receiveAsFlow()

    private var deletedTodo: Todo?=null

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.onTodoClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO+"?todoId=${event.todo.id}"))
            }
            is TodoListEvent.onAddTodoClick->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.onUndoDeleteClick->{
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.DeleteTodo->{
                viewModelScope.launch{
                    deletedTodo=event.todo
                    repository.deletetodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackbar("Todo Deleted", action = "Undo"))
                }
            }
            is TodoListEvent.onDoneChange->{
                viewModelScope.launch {
                    repository.insertTodo(event.todo.copy(isComplted = event.isDone))
                }
            }


        }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}