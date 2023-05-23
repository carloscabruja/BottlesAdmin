package es.refil.bottlesadmin.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import es.refil.bottlesadmin.common.Constants.ACTION_ADD_BOTTLE

@Composable
fun AddBottleFloatingActionButton(
    openDialog: () -> Unit
) {
    FloatingActionButton(
        onClick = openDialog,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ACTION_ADD_BOTTLE
        )
    }
}