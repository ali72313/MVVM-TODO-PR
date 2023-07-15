package com.example.mvvm_todo_project.data.models

import androidx.compose.ui.graphics.Color
import com.example.mvvm_todo_project.ui.theme.LowPriorityColor

enum class Priority(val color: Color) {
    HIGH(Color.Red.copy(alpha = 0.6f)),
    MEDIUM(Color.Yellow.copy(alpha = 0.6f)),
    LOW(LowPriorityColor),
    NONE(Color.White)
}
