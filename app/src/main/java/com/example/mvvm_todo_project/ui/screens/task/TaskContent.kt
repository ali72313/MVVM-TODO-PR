package com.example.mvvm_todo_project.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.components.PriorityItemDropDown
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.ui.theme.MEDIUM_PADDING


@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)

    ) {
        OutlinedTextField(
            value = title,
            label = {
                Text(
                    text = "title",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            },
            onValueChange = { onTitleChange(it) },
            modifier = Modifier.fillMaxWidth(),

            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            placeholder = {
                Text(
                    text = "keep it less than 30 character",
                    modifier = Modifier.alpha(0.38f),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        PriorityItemDropDown(priority = priority, onPrioritySelected = onPrioritySelected)
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        OutlinedTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            modifier = Modifier.fillMaxSize(),
            textStyle = MaterialTheme.typography.bodyMedium,
            label = {
                Text(
                    text = "description",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            },
            placeholder = {
                Text(
                    text = "description",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.alpha(0.38f)
                )
            }

        )


    }
}

@Composable
@Preview
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.MEDIUM,
        onPrioritySelected = {}
    )
}