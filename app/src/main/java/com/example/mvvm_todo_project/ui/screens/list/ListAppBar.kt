package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvm_todo_project.ui.theme.Purple40
import com.example.mvvm_todo_project.ui.theme.topAppBarColor


@Composable
fun ListAppBar() {
    DefaultListAppBar {}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        actions = {
            ListAppBarActions(onSearchClicked)
        },
        title = {
            Text(
                text = "Tasks",
                textAlign = TextAlign.Right,
                color = topAppBarColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Purple40)
    )
}

@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit) {
    SearchAction(onSearchClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Tasks",
            tint = topAppBarColor
        )


    }
}


@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultListAppBar {}
}