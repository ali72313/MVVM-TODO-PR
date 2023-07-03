package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.ui.theme.fabBackGroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navigateToTaskScreen: (Int) -> Unit) {
    Scaffold(
        topBar = {
            ListAppBar()
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }

    ) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}

@Composable
fun ListFab(navigateToTaskScreen: (Int) -> Unit) {

    FloatingActionButton(containerColor = fabBackGroundColor,
        onClick = { navigateToTaskScreen(-1) }
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }

}


@Composable
@Preview
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}
