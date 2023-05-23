package es.refil.bottlesadmin.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import es.refil.bottlesadmin.common.Constants.ACTION_ADD_BOTTLE
import es.refil.bottlesadmin.common.Constants.BUTTON_ADD
import es.refil.bottlesadmin.common.Constants.BUTTON_CANCEL
import es.refil.bottlesadmin.common.Constants.NO_BARCODE
import es.refil.bottlesadmin.common.Constants.NO_POINTS
import es.refil.bottlesadmin.common.Constants.NO_SIZE
import es.refil.bottlesadmin.common.Constants.NO_TYPE
import es.refil.bottlesadmin.common.Constants.NO_VALUE
import es.refil.bottlesadmin.presentation.MainViewModel
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottleAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: (barcode: String, points: Int, size: Int, type: String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    var points by remember { mutableStateOf(NO_VALUE) }
    var size by remember { mutableStateOf(NO_VALUE) }
    var type by remember { mutableStateOf(NO_VALUE) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = ACTION_ADD_BOTTLE) },
        text = {
            Column {
                Text(
                    text = viewModel.barCode,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Button(onClick = { viewModel.startScanning() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Scan barcode")
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                TextField(
                    value = points,
                    onValueChange = { points = it },
                    placeholder = { Text(text = NO_POINTS) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                TextField(
                    value = size,
                    onValueChange = { size = it },
                    placeholder = { Text(text = NO_SIZE) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                TextField(
                    value = type,
                    onValueChange = { type = it },
                    placeholder = { Text(text = NO_TYPE) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val barcode = viewModel.barCode ?: NO_BARCODE
                onConfirm(barcode, points.toInt(), size.toInt(), type)
                onDismiss()
            }) {
                Text(text = BUTTON_ADD)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = BUTTON_CANCEL)
            }
        }
    )
}