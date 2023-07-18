package com.example.mvvm_todo_project.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.R
import com.example.mvvm_todo_project.components.DisplayAlertDialog
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.ui.theme.topAppBarContainerColor
import com.example.mvvm_todo_project.ui.theme.topAppBarContentColor


@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit, task: TodoTask?) {
    if (task == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        SelectedTaskAppBar(navigateToListScreen = navigateToListScreen, todoTask = task)
    }
    // NewTaskAppBar(navigateToListScreen = navigateToListScreen)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {

    TopAppBar(
        title = { Text(text = "Add Task", color = topAppBarContentColor) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = topAppBarContainerColor),
        navigationIcon = {
            BackToListScreen(onBackClicked = navigateToListScreen)
        }, actions = {
            NewTaskBarActions(navigateToListScreen)
        }
    )
}

@Composable
fun BackToListScreen(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            tint = topAppBarContentColor,
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back to list screen",
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun NewTaskBarActions(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "Creating new task",
            tint = topAppBarContentColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedTaskAppBar(navigateToListScreen: (Action) -> Unit, todoTask: TodoTask) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = topAppBarContainerColor),
        title = {
            todoTask?.let {
                Text(
                    text = it.title,
                    color = topAppBarContentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            CancelCreatingTask(onCancelClicked = navigateToListScreen)

        }, actions =
        {
            SelectedTaskAppBarActions(
                navigateToListScreen = navigateToListScreen,
                todoTask = todoTask
            )
        }
    )

}

@Composable
fun CancelCreatingTask(onCancelClicked: (Action) -> Unit) {
    IconButton(onClick = { onCancelClicked(Action.NO_ACTION) }) {
        Icon(
            tint = topAppBarContentColor,
            imageVector = Icons.Default.Close,
            contentDescription = "back to list screen",
            modifier = Modifier
                .padding(8.dp)
        )
    }

}

@Composable
fun SelectedTaskAppBarActions(
    navigateToListScreen: (Action) -> Unit,
    todoTask: TodoTask
) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, todoTask.title),
        message = stringResource(id = R.string.delete_task_conformation, todoTask.title),
        openDialog = openDialog,
        onYesClicked = { navigateToListScreen(Action.DELETE) },
        closeDialog = { openDialog = false })
    DeleteTaskAction(onDeleteTaskAction = { openDialog = true })
    UpdateTaskAction(onUpdateTaskAction = navigateToListScreen)
}

@Composable
fun DeleteTaskAction(onDeleteTaskAction: () -> Unit) {
    IconButton(onClick = { onDeleteTaskAction() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.search_tasks),
            tint = topAppBarContentColor
        )


    }
}

@Composable
fun UpdateTaskAction(onUpdateTaskAction: (Action) -> Unit) {
    IconButton(onClick = { onUpdateTaskAction(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = stringResource(id = R.string.search_tasks),
            tint = topAppBarContentColor
        )


    }
}

@Composable
@Preview
fun SelectedTaskAppBarPreview() {
    SelectedTaskAppBar({}, TodoTask(2, "ali", "meh", Priority.LOW))
}


@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar {}
}


