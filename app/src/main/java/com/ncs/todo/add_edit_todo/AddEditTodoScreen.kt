package com.ncs.todo.add_edit_todo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncs.todo.Util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val scaffoldState = remember{ SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.onEvent(AddEditTodoEvent.onSaveTodoClick)
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save"
                    )
                }
            }
        ) {contentPadding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                Box(modifier = Modifier.padding(start = 10.dp, top = 150.dp, end = 10.dp)) {
                    TextField(
                        value = viewModel.title,
                        onValueChange = {
                            viewModel.onEvent(AddEditTodoEvent.onTitleChnage(it))
                        },
                        placeholder = {
                            Text(text = "Title", color = Color.White)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.padding(start = 10.dp, top = 50.dp, end = 10.dp)) {
                    TextField(
                        value = viewModel.description,
                        onValueChange = {
                            viewModel.onEvent(AddEditTodoEvent.onDescriptionChange(it))
                        },
                        placeholder = {
                            Text(text = "Description", color = Color.White)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 5
                    )
                }
            }
        }


}