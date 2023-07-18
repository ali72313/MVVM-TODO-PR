package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.R
import com.example.mvvm_todo_project.components.DisplayAlertDialog
import com.example.mvvm_todo_project.components.PriorityItem
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.SearchAppBarState
import com.example.mvvm_todo_project.data.utils.TrailingIconState
import com.example.mvvm_todo_project.ui.theme.LARGE_PADDING
import com.example.mvvm_todo_project.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.mvvm_todo_project.ui.theme.topAppBarContentColor
import com.example.mvvm_todo_project.ui.theme.topAppBarContainerColor
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel


@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel, searchAppBarState: SearchAppBarState, searchTextState: String
) {


    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(onSearchClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
            }, onSortClicked = {}, DeleteAllTasks = {
                sharedViewModel.action.value = Action.DELETE_ALL
            })
        }

        else -> {
            SearchAppBar(text = searchTextState,
                onTextChange = { sharedViewModel.searchTextState.value = it },
                onCloseClicked = {
                    sharedViewModel.searchTextState.value = ""
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED

                }, onSearchClicked = { sharedViewModel.searchDataBase(it) })
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, DeleteAllTasks: () -> Unit
) {
    TopAppBar(actions = {
        ListAppBarActions(onSearchClicked, onSortClicked, DeleteAllTasks)

    }, title = {
        Text(
            text = "Tasks", textAlign = TextAlign.Right, color = topAppBarContentColor
        )
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = topAppBarContainerColor)
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, DeleteAllTasks: () -> Unit
) {
    var openDialog by remember() { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, "All Tasks "),
        message = stringResource(id = R.string.delete_task_conformation, "All Tasks "),
        openDialog = openDialog,
        onYesClicked = { DeleteAllTasks() }, closeDialog = { openDialog = false })
    SearchAction(onSearchClicked)
    SortAction(sortByPriority = onSortClicked)
    DeleteAllAction(openDialog = { openDialog = true })
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_tasks),
            tint = topAppBarContentColor
        )


    }
}

@Composable
fun SortAction(sortByPriority: (Priority) -> Unit) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }


    IconButton(onClick = { isExpanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_priority),
            contentDescription = "Sort_tasks",
            tint = topAppBarContentColor
        )
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(text = { PriorityItem(priority = Priority.HIGH) }, onClick = {
                isExpanded = false
                sortByPriority(Priority.HIGH)
            })
            DropdownMenuItem(text = { PriorityItem(priority = Priority.LOW) }, onClick = {
                isExpanded = false
                sortByPriority(Priority.LOW)
            })
            DropdownMenuItem(text = { PriorityItem(priority = Priority.NONE) }, onClick = {
                isExpanded = false
                sortByPriority(Priority.NONE)
            })
        }

    }
}

@Composable
fun DeleteAllAction(openDialog: () -> Unit) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = { isExpanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "more tasks",
            tint = topAppBarContentColor
        )
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(onClick = {
                isExpanded = false
                openDialog.invoke()
            }, text = {
                Text(
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    text = "Delete All Tasks!",
                    style = MaterialTheme.typography.titleMedium
                )
            })
        }
    }
}


@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by rememberSaveable {
        mutableStateOf(TrailingIconState.READY_TO_CLOSE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        color = topAppBarContainerColor

    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            value = text,
            textStyle = TextStyle(
                color = topAppBarContentColor,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            ),
            singleLine = true,
            onValueChange = { onTextChange(it) },
            leadingIcon = {
                Icon(
                    modifier = Modifier.alpha(0.38f),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search_Icon",
                    tint = topAppBarContentColor
                )

            },
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.alpha(0.38f)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                                onTextChange("")

                            }

                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }

                            }

                        }
                    },
                ) {
                    Icon(
                        tint = topAppBarContentColor,
                        imageVector = Icons.Filled.Close,
                        contentDescription = "exit"
                    )
                }

            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),

            )
    }
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar("", {}, {}, {})
}

@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultListAppBar({}, {}, {})
}