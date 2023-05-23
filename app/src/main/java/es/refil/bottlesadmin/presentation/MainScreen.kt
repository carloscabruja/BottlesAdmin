package es.refil.bottlesadmin.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import es.refil.bottlesadmin.common.TopBar
import es.refil.bottlesadmin.presentation.components.AddBottle
import es.refil.bottlesadmin.presentation.components.AddBottleAlertDialog
import es.refil.bottlesadmin.presentation.components.AddBottleFloatingActionButton
import es.refil.bottlesadmin.presentation.components.Bottles
import es.refil.bottlesadmin.presentation.components.BottlesContent
import es.refil.bottlesadmin.presentation.components.DeleteBottle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar() },
        content = { paddingValues ->
            Bottles(
                bottlesContent = { bottles ->
                    BottlesContent(
                        padding = paddingValues,
                        bottles = bottles,
                        deleteBottle = { barcode ->
                            viewModel.deleteBottle(barcode)
                        }
                    )
                    if (openDialog) {
                        AddBottleAlertDialog(
                            onDismiss = { openDialog = false },
                            onConfirm = { barcode, points, size, type ->
                                viewModel.addBottle(barcode, points, size, type)
                                openDialog = false
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            AddBottleFloatingActionButton(
                openDialog = { openDialog = true }
            )
        }
    )
    AddBottle()
    DeleteBottle()
}