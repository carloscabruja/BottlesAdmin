package es.refil.bottlesadmin.presentation.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.refil.bottlesadmin.common.ProgressBar
import es.refil.bottlesadmin.domain.model.Response
import es.refil.bottlesadmin.domain.repository.Bottles
import es.refil.bottlesadmin.presentation.MainViewModel

@Composable
fun Bottles(
    viewModel: MainViewModel = hiltViewModel(),
    bottlesContent: @Composable (bottles: Bottles) -> Unit
) {
    when (val bottlesResponse = viewModel.bottles) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> bottlesContent(bottlesResponse.data)
        is Response.Failure -> Log.d(
            "BottlesException",
            "BottlesException: ${bottlesResponse.exception}"
        )
    }
}