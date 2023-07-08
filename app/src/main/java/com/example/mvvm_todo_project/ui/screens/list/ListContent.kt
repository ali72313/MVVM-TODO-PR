package com.example.mvvm_todo_project.ui.screens.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvm_todo_project.data.models.Priority
import com.example.mvvm_todo_project.data.models.TodoTask
import com.example.mvvm_todo_project.ui.theme.LARGE_PADDING
import com.example.mvvm_todo_project.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.mvvm_todo_project.ui.theme.taskItemBackGroundColor
import com.example.mvvm_todo_project.ui.theme.taskItemTextColor

@Composable
fun ListContent() {

}

@Composable
fun ListItem(
    task: TodoTask,
    navigateTodoTask: (taskId: Int) -> Unit
) {
    /* Surface(
         modifier = Modifier.fillMaxWidth(),
         color = taskItemBackGroundColor,
         shape = RectangleShape,
         shadowElevation = 2.dp,
         onClick = { navigateTodoTask(task.id) }
     ) */
    Card(
        shape = RoundedCornerShape(8.dp),
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
                        .weight(1f), contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
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
            Priority.Low
        )
    ) {}
}