package com.example.mvvm_todo_project.ui.screens.list

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog


@Composable
fun TestComposable() {

    var backgroundChange by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {backgroundChange = !backgroundChange},
        colors = ButtonDefaults.buttonColors(containerColor = if (backgroundChange) Color.Blue else Color.Red)
    ) {
        Text(text = "ali")
    }



}

@Composable
@Preview
fun TestComposablePreview() {
    TestComposable()
}