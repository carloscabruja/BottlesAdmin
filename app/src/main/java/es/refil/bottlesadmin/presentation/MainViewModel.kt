package es.refil.bottlesadmin.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.refil.bottlesadmin.common.Constants.NO_BARCODE
import es.refil.bottlesadmin.domain.model.Response
import es.refil.bottlesadmin.domain.repository.AddBottleResponse
import es.refil.bottlesadmin.domain.repository.BottlesResponse
import es.refil.bottlesadmin.domain.repository.DeleteBottleResponse
import es.refil.bottlesadmin.domain.use_case.UseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    var bottles by mutableStateOf<BottlesResponse>(Response.Loading)
        private set
    var addBottleResponse by mutableStateOf<AddBottleResponse>(Response.Success(false))
        private set
    var deleteBottleResponse by mutableStateOf<DeleteBottleResponse>(Response.Success(false))
        private set
    var barCode by mutableStateOf(NO_BARCODE)
        private set

    init {
        getBottles()
    }

    private fun getBottles() = viewModelScope.launch {
        useCases.getBottlesFromFirestore().collect { response ->
            bottles = response
        }
    }

    fun addBottle(barcode: String, points: Int, size: Int, type: String) = viewModelScope.launch {
        addBottleResponse = Response.Loading
        addBottleResponse = useCases.addBottleToFirestore(barcode, points, size, type)
    }

    fun deleteBottle(barcode: String) = viewModelScope.launch {
        deleteBottleResponse = Response.Loading
        deleteBottleResponse = useCases.deleteBottleFromFirestore(barcode)
    }

    fun startScanning() = viewModelScope.launch {
        useCases.startScanning().collect {
            if (!it.isNullOrBlank()) {
                barCode = it
            }
        }
    }
}
