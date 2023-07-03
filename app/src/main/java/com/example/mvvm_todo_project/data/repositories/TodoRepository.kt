package com.example.mvvm_todo_project.data.repositories

import com.example.mvvm_todo_project.data.TodoDao
import com.example.mvvm_todo_project.data.models.TodoTask
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton



class TodoRepository @Inject constructor(private val todoDao: TodoDao) {


    val getAllTasks: Flow<List<TodoTask>> = todoDao.getAllTasks()
    val sorByHighPriority: Flow<List<TodoTask>> = todoDao.sortByHighPriority()
    val sorByLowPriority: Flow<List<TodoTask>> = todoDao.sortByLowPriority()
    fun getTask(id: Int): Flow<TodoTask> = todoDao.getTask(id)
    suspend fun addTask(task: TodoTask) = todoDao.newTask(task)
    suspend fun deleteTask(task: TodoTask) = todoDao.deleteTask(task)
    suspend fun deleteAll() = todoDao.deleteAllTasks()
    suspend fun update(task: TodoTask) = todoDao.updateTask(task)
    fun searchTask(searchQuery: String): Flow<List<TodoTask>> = todoDao.searchTask(searchQuery)

}