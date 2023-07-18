package com.example.mvvm_todo_project.ui.screens.list


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.ui.theme.fabBackGroundColor
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel
import kotlinx.coroutines.launch


@Composable
fun ListUiScreen(navigateToTaskScreen: (Int) -> Unit, sharedViewModel: SharedViewModel) {

    /*LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        Log.d("ListScreen", "LaunchEffect Triggered")
    }*/
    val action by sharedViewModel.action

    val allTasks = sharedViewModel.allTask.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    // val allTasks by  sharedViewModel.allTask.collectAsState() these two lines do the same thing , but when we use by we dont need to use  .value
    //val searchAppbarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchAppbarState =
        sharedViewModel.searchAppBarState.value //this and the line above do the same thing

    val searchTextState: String by sharedViewModel.searchTextState


    // sharedViewModel.handleDataBaseActions(action)
    DisplaySnackBar(
        snackbarHostState = snackbarHostState,
        handleDataBaseAction = { sharedViewModel.handleDataBaseActions(action) },
        onUndoClicked = { sharedViewModel.action.value = it },
        taskTitle = sharedViewModel.title.value,
        action = sharedViewModel.action.value
    )
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
            ListContent(
                allTasks = allTasks.value,
                searchedTasks = searchedTasks,
                navigateTodoTask = navigateToTaskScreen,
                searchAppBarState = searchAppbarState
            )
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
fun DisplaySnackBar(
    snackbarHostState: SnackbarHostState,
    handleDataBaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDataBaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action)
    {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = setMessage(action, todoTaskTitle = taskTitle),
                    actionLabel = setActionLabel(action),
                    duration = SnackbarDuration.Long
                )
                undoDeletedTask(action, snackBarResult = snackbarResult, onUndoClicked)
            }
        }
    }
}

private fun setMessage(action: Action, todoTaskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All tasks are deleted."
        else -> "${action.name}  : $todoTaskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action == Action.DELETE) {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}


