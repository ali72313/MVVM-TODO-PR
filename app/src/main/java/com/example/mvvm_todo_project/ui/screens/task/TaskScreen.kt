package com.example.mvvm_todo_project.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit, task: TodoTask?, sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

  //  HandleBackPressed(onBackPressed = { navigateToListScreen(Action.NO_ACTION) })
    BackHandler(onBack = { navigateToListScreen(Action.NO_ACTION) })

    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = { action ->
            if (action == Action.NO_ACTION) {
                navigateToListScreen(action)
            } else {
                if (sharedViewModel.validateTitleAndDescription()) {
                    navigateToListScreen(action)
                } else {
                    displayToast(context = context)
                }
            }
        }, task = task)
    }) {
        Surface(Modifier.padding(it)) {
            TaskContent(
                title = title,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                description = description,
                onDescriptionChange = { sharedViewModel.description.value = it },
                priority = priority,
                onPrioritySelected = { sharedViewModel.priority.value = it }
            )
        }

    }
}

fun displayToast(context: Context) {
    Toast.makeText(context, "title or description can not be empty!!", Toast.LENGTH_SHORT).show()
}

@Composable
fun HandleBackPressed(
    backDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
    val backCallBack = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }
    DisposableEffect(key1 = backDispatcher)
    {
        backDispatcher?.addCallback(backCallBack)
        onDispose { backCallBack.remove() }
    }
}
