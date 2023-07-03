package com.example.mvvm_todo_project.ui.theme


import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Teal200 = Color(0xFF03DAC5)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LowPriorityColor = Color(0xff00c980)

val topAppBarColor
    @Composable
    get() =
        if (isSystemInDarkTheme()) Color.White else Color.Gray

val fabBackGroundColor
    @Composable
    get() =
        if (isSystemInDarkTheme()) Purple40 else Teal200

