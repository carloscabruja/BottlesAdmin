package es.refil.bottlesadmin.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.refil.bottlesadmin.domain.repository.Bottles

@Composable
fun BottlesContent(
    padding: PaddingValues,
    bottles: Bottles,
    deleteBottle: (barcode: String) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(bottles.size) { index ->
            BottleCard(
                bottle = bottles[index],
                deleteBottle = { deleteBottle(bottles[index].barcode!!) }
            )
        }
    }
}