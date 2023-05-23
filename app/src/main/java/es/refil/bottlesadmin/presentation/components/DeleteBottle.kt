package es.refil.bottlesadmin.presentation.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.refil.bottlesadmin.common.ProgressBar
import es.refil.bottlesadmin.domain.model.Response
import es.refil.bottlesadmin.presentation.MainViewModel

@Composable
fun DeleteBottle(
    viewModel: MainViewModel = hiltViewModel()
) {
    when (val deleteBottleResponse = viewModel.deleteBottleResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> Log.d(
            "BottlesException",
            "BottlesException: ${deleteBottleResponse.exception}"
        )
    }
}