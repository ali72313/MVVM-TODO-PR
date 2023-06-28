package com.example.mvvm_todo_project.data.models

import androidx.room.Entity
import com.example.mvvm_todo_project.data.constants.Constants
import java.util.UUID


@Entity(tableName = Constants.DATABASE_TABLE)
data class TodoTask(
    val id: UUID,
) {
}