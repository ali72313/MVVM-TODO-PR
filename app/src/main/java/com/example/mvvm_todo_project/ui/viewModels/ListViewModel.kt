package com.example.mvvm_todo_project.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.mvvm_todo_project.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel(){




}