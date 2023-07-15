package com.example.mvvm_todo_project.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.data.repositories.TodoRepository
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.RequestState
import com.example.mvvm_todo_project.data.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableIntStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("0")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)


    val searchAppBarState = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState = mutableStateOf("")


    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTask
        get() = _allTasks.asStateFlow()

    init {

        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.getAllTasks.collect()
                {
                    _allTasks.value = RequestState.Success(it)
                }
            }


        } catch (ex: Exception) {
            _allTasks.value = RequestState.Error(ex)
        }
    }

    private val _selectedTask: MutableStateFlow<TodoTask?> = MutableStateFlow(null)
    val selectedTask
        get() = _selectedTask.asStateFlow()

    fun getSelectedTask(id: Int) {
        viewModelScope.launch {
            todoRepository.getTask(id).collect()
            {
                _selectedTask.value = it
            }
        }
    }

    fun updateTaskField(selectedTask: TodoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW

        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < 30) {
            title.value = newTitle
        }
    }

    private fun addTask() {
        viewModelScope.launch {
            val todoTask =
                TodoTask(
                    title = title.value,
                    description = description.value,
                    priority = priority.value
                )
            todoRepository.addTask(todoTask)
        }
    }

     fun handleDataBaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }

            Action.UPDATE -> {
                addTask()
            }

            Action.DELETE -> {
                addTask()
            }

            Action.DELETE_ALL -> {
                addTask()
            }

            Action.UNDO -> {
                addTask()
            }


            else -> {}
        }
        this.action.value = Action.NO_ACTION

    }

    fun validateTitleAndDescription(): Boolean {
        return (title.value.isNotEmpty() && description.value.isNotEmpty())
    }
}