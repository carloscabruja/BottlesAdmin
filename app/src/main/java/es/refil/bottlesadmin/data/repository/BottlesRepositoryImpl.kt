package es.refil.bottlesadmin.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import es.refil.bottlesadmin.common.Constants.FIRESTORE_FIELD_POINTS
import es.refil.bottlesadmin.domain.model.Bottle
import es.refil.bottlesadmin.domain.model.Response.Failure
import es.refil.bottlesadmin.domain.model.Response.Success
import es.refil.bottlesadmin.domain.repository.AddBottleResponse
import es.refil.bottlesadmin.domain.repository.BottlesRepository
import es.refil.bottlesadmin.domain.repository.BottlesResponse
import es.refil.bottlesadmin.domain.repository.DeleteBottleResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BottlesRepositoryImpl @Inject constructor(
    private val bottlesRef: CollectionReference,
    private val scanner: GmsBarcodeScanner,
) : BottlesRepository {
    override fun getBottlesFromFirestore(): Flow<BottlesResponse> = callbackFlow {
        val snapshotListener = bottlesRef.orderBy(FIRESTORE_FIELD_POINTS)
            .addSnapshotListener { querySnapshot, FirebaseException ->
                val bottlesResponse = if (querySnapshot != null) {
                    val bottles = querySnapshot.toObjects(Bottle::class.java)
                    Success(bottles)
                } else {
                    Failure(FirebaseException)
                }
                trySend(bottlesResponse)
            }
        awaitClose { snapshotListener.remove() }
    }

    override suspend fun addBottleToFirestore(
        barcode: String,
        points: Int,
        size: Int,
        type: String
    ): AddBottleResponse = try {
        bottlesRef.document(barcode).set(Bottle(barcode, points, size, type)).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteBottleFromFirestore(barcode: String): DeleteBottleResponse = try {
        bottlesRef.document(barcode).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun startScanning(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()
                .addOnSuccessListener {
                    launch {
                        send(it.rawValue)
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }
            awaitClose { }
        }
    }
}