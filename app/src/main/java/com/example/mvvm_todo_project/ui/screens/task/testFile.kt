package com.example.mvvm_todo_project.ui.screens.task

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun tesFile() {
    var testState by rememberSaveable {
        mutableStateOf(false)
    }

}

@Composable
@Preview
fun TestFilePreview() {
    tesFile()
}