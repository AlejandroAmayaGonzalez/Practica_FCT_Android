package com.aamagon.practica_fct_android.ui.view.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.aamagon.practica_fct_android.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BillDialog(show: Boolean, onDismiss: () -> Unit){
    if (show){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {},
            dismissButton = {
                TextButton( onClick = { onDismiss() } ) {
                    Text(
                        text = stringResource(R.string.dismissButton),
                        fontSize = 20.sp
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.titleDialog),
                    fontSize = 30.sp
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.bodyDialog),
                    fontSize = 20.sp
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    dateString: MutableState<String>,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
){
    val datePickerState = rememberDatePickerState()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss
                dateString.value =
                    dateFormat.format(Date(datePickerState.selectedDateMillis!!)).toString()
            }) {
                Text(text = stringResource(R.string.acceptDialog))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss }) {
                Text(text = stringResource(R.string.cancelDialog))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}