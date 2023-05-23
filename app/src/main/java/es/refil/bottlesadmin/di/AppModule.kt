package es.refil.bottlesadmin.di

import android.app.Application
import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.refil.bottlesadmin.common.Constants.FIRESTORE_COLLECTION_BOTTLES
import es.refil.bottlesadmin.data.repository.BottlesRepositoryImpl
import es.refil.bottlesadmin.domain.repository.BottlesRepository
import es.refil.bottlesadmin.domain.use_case.AddBottleToFirestore
import es.refil.bottlesadmin.domain.use_case.DeleteBottleFromFirestore
import es.refil.bottlesadmin.domain.use_case.GetBottlesFromFirestore
import es.refil.bottlesadmin.domain.use_case.StartScanning
import es.refil.bottlesadmin.domain.use_case.UseCases

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideBarCodeOptions(): GmsBarcodeScannerOptions {
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }

    @Provides
    @Singleton
    fun provideBarCodeScanner(
        context: Context,
        options: GmsBarcodeScannerOptions
    ): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context, options)
    }

    @Provides
    @Singleton
    fun provideBottlesRef() = Firebase.firestore.collection(FIRESTORE_COLLECTION_BOTTLES)

    @Provides
    @Singleton
    fun provideBottlesRepository(
        bottlesRef: CollectionReference,
        scanner: GmsBarcodeScanner
    ): BottlesRepository = BottlesRepositoryImpl(bottlesRef, scanner)

    @Provides
    @Singleton
    fun provideBottlesUseCases(bottlesRepository: BottlesRepository) = UseCases(
        getBottlesFromFirestore = GetBottlesFromFirestore(bottlesRepository),
        addBottleToFirestore = AddBottleToFirestore(bottlesRepository),
        deleteBottleFromFirestore = DeleteBottleFromFirestore(bottlesRepository),
        startScanning = StartScanning(bottlesRepository)
    )

}