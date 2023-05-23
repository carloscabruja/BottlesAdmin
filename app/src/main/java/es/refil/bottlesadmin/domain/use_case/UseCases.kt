package es.refil.bottlesadmin.domain.use_case

data class UseCases(
    val getBottlesFromFirestore: GetBottlesFromFirestore,
    val addBottleToFirestore: AddBottleToFirestore,
    val deleteBottleFromFirestore: DeleteBottleFromFirestore,
    val startScanning: StartScanning
)
