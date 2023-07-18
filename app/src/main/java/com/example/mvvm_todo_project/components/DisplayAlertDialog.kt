package com.example.mvvm_todo_project.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    onYesClicked: () -> Unit,
    closeDialog: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                closeDialog()
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        text = title,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Text(
                        text = message, maxLines = 2, overflow = TextOverflow.Ellipsis,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {

                        TextButton(
                            onClick = {
                                closeDialog()
                            }
                        ) {
                            Text("cancel")
                        }

                        TextButton(
                            onClick = {
                                onYesClicked()
                                closeDialog()
                            }
                        ) {
                            Text("Confirm")
                        }


                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun DisplayDialogPreview() {
    DisplayAlertDialog(
        title = "ali",
        message = "ali message ali messageali messageali messageali messageali messageali message",
        openDialog = true,
        onYesClicked = { /*TODO*/ }, closeDialog = {})

}
