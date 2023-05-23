package es.refil.bottlesadmin.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.refil.bottlesadmin.domain.model.Bottle

@Composable
fun BottleCard(
    bottle: Bottle,
    deleteBottle: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = 4.dp,
                end = 4.dp
            )
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                bottle.barcode?.let { barcode ->
                    Text(
                        text = "Barcode: $barcode",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                bottle.points?.let { points ->
                    Text(
                        text = "Points: $points",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                bottle.size?.let { size ->
                    Text(
                        text = "Size: $size",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                bottle.type?.let { type ->
                    Text(
                        text = "Type: $type",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteBottle = deleteBottle
            )
        }
    }
}
