package es.refil.bottlesadmin.presentation.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.refil.bottlesadmin.common.ProgressBar
import es.refil.bottlesadmin.domain.model.Response
import es.refil.bottlesadmin.presentation.MainViewModel

@Composable
fun AddBottle(
    viewModel: MainViewModel = hiltViewModel()
) {
    when (val addBottleResponse = viewModel.addBottleResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> Log.d(
            "BottlesException",
            "BottlesException: ${addBottleResponse.exception}"
        )
    }
}