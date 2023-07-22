package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.RequestState
import com.example.mvvm_todo_project.data.utils.SearchAppBarState
import com.example.mvvm_todo_project.ui.theme.LARGE_PADDING
import com.example.mvvm_todo_project.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.mvvm_todo_project.ui.theme.taskItemBackGroundColor
import com.example.mvvm_todo_project.ui.theme.taskItemTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    allTasks: RequestState<List<TodoTask>>,
    searchedTasks: RequestState<List<TodoTask>>,
    highPriorityTask: List<TodoTask>,
    lowPriorityTask: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    sortState: RequestState<Priority>,
    navigateTodoTask: (taskId: Int) -> Unit,
    searchAppBarState: SearchAppBarState
) {

    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateTodoTask = navigateTodoTask
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateTodoTask = navigateTodoTask
                    )
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTask,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateTodoTask = navigateTodoTask
                )
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTask,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateTodoTask = navigateTodoTask
                )
            }
        }
    }

}

@Composable
fun RedBackGround(degrees: Float) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Red.copy(alpha = 0.6f))
            .padding(24.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = Color.White,
            modifier = Modifier
                .rotate(degrees)
                .padding(end = 8.dp)

        )
    }

}

@Composable
@Preview
fun RedBackGroundPreview() {
    RedBackGround(degrees = 20f)
}

@Composable
fun HandleListContent(
    tasks: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateTodoTask: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayAllTasks(
            allTasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateTodoTaskScreen = navigateTodoTask
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DisplayAllTasks(
    allTasks: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateTodoTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(items = allTasks, key = { task -> task.id }) { item ->
            val dismissState = rememberDismissState()
            //   val dismissProgression = dismissState.progress
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
            if (isDismissed && dismissDirection == DismissDirection.EndToStart
            ) {
                val scope = rememberCoroutineScope()
                LaunchedEffect(key1 = true)
                {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, item)
                    }
                }


            }
            val degrees by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f)

            var itemAppeared by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = true)
            {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(animationSpec = tween(durationMillis = 300)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 300))
            ) {
                SwipeToDismiss(state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = { RedBackGround(degrees = degrees) }, dismissContent =
                    {
                        ListItem(task = item, navigateTodoTask = navigateTodoTaskScreen)
                    })
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    task: TodoTask, navigateTodoTask: (taskId: Int) -> Unit
) {

    Card(
        onClick = { navigateTodoTask(task.id) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = taskItemBackGroundColor),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    text = task.title,
                    modifier = Modifier.weight(8f),
                    color = taskItemTextColor,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = task.priority.color)
                    }
                }

            }
            Text(
                text = task.description,
                Modifier.fillMaxWidth(),
                color = taskItemTextColor,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }


    }

}

@Composable
@Preview
fun ListItemPreview() {
    ListItem(
        TodoTask(
            0,
            "test",
            "mumble jumble is kind of sad junlge mumble whitch all of the animals in the see dont want to be ",
            Priority.LOW
        )
    ) {}
}