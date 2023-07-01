package com.example.mvvm_todo_project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_todo_project.data.models.TodoTask


@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDataBase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}