package com.example.mvvm_todo_project.data.models

import androidx.compose.ui.graphics.Color

enum class Priority(color: Color) {
    High(Color.Red.copy(alpha = 0.6f)),
    Medium(Color.Yellow.copy(alpha = 0.6f)),
    Low(Color.Green.copy(alpha = 0.6f)),
    None(Color.White)
}