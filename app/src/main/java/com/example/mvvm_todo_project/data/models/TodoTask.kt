package com.example.mvvm_todo_project.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvm_todo_project.data.utils.Constants


@Entity(tableName = Constants.DATABASE_TABLE)
data class TodoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)