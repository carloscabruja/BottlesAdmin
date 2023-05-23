package es.refil.bottlesadmin.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import es.refil.bottlesadmin.common.Constants.ACTION_DELETE_BOTTLE

@Composable
fun DeleteIcon(
    deleteBottle: () -> Unit
) {
    IconButton(
        onClick = deleteBottle,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = ACTION_DELETE_BOTTLE,
        )
    }
}