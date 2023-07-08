package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mvvm_todo_project.ui.theme.fabBackGroundColor
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel


@Composable
fun ListScreen(navigateToTaskScreen: (Int) -> Unit, sharedViewModel: SharedViewModel) {

    //val searchAppbarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchAppbarState = sharedViewModel.searchAppBarState.value
    val searchTextState: String by sharedViewModel.searchTextState
    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppbarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }

    ) {
        Surface(modifier = Modifier.padding(it)) {
            ListContent()
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


