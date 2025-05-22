package com.aamagon.practica_fct_android.ui.view.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.theme.Green
import com.aamagon.practica_fct_android.ui.theme.LightGreen
import com.aamagon.practica_fct_android.ui.theme.White
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
                TextButton(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.textButtonColors(contentColor = Green)
                ) {
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
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss
                    /* If the user clicks accept before pick a date set the actual
                    in the filter */
                    if (datePickerState.selectedDateMillis != null){
                        dateString.value = dateFormat
                            .format(Date(datePickerState.selectedDateMillis!!)).toString()
                    }else{
                        dateString.value = dateFormat
                            .format(Date()).toString()
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = LightGreen,
                    contentColor = White
                )
            ) {
                Text(text = stringResource(R.string.acceptDialog))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = LightGreen,
                    contentColor = White
                )
            ) {
                Text(text = stringResource(R.string.cancelDialog))
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = LightGreen,
                todayDateBorderColor = Green,
                todayContentColor = Green,
                selectedYearContainerColor = LightGreen
            )
        )
    }
}

@Composable
fun InfoAboutStateDialog(onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = LightGreen,
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.acceptDialog),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = White,
                )
            }
        },
        dismissButton = {},
        title = {
            Text(
                text = stringResource(R.string.detailTitle),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.detailBody),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}