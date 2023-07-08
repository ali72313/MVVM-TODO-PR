package com.example.mvvm_todo_project.data.models

import androidx.compose.ui.graphics.Color
import com.example.mvvm_todo_project.ui.theme.LowPriorityColor

enum class Priority(val color: Color) {
    High(Color.Red.copy(alpha = 0.6f)),
    Medium(Color.Yellow.copy(alpha = 0.6f)),
    Low(LowPriorityColor),
    None(Color.White)
}
