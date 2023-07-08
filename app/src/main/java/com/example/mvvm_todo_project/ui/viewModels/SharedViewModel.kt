package com.example.mvvm_todo_project.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.data.repositories.TodoRepository
import com.example.mvvm_todo_project.data.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    val searchAppBarState = mutableStateOf(SearchAppBarState.CLOSED)
     val searchTextState = mutableStateOf("")

    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTask
        get() = _allTasks.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            todoRepository.getAllTasks.collect()
            {
                _allTasks.value = it
            }
        }
    }
}