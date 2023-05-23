package es.refil.bottlesadmin.domain.repository

import es.refil.bottlesadmin.domain.model.Bottle
import es.refil.bottlesadmin.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Bottles = List<Bottle>
typealias BottlesResponse = Response<Bottles>
typealias AddBottleResponse = Response<Boolean>
typealias DeleteBottleResponse = Response<Boolean>

interface BottlesRepository {
    fun getBottlesFromFirestore(): Flow<BottlesResponse>

    suspend fun addBottleToFirestore(
        barcode: String,
        points: Int,
        size: Int,
        type: String
    ): AddBottleResponse

    suspend fun deleteBottleFromFirestore(barcode: String): DeleteBottleResponse

    fun startScanning(): Flow<String?>
}