package es.refil.bottlesadmin.domain.use_case

import es.refil.bottlesadmin.domain.repository.BottlesRepository

class DeleteBottleFromFirestore(
    private val bottlesRepository: BottlesRepository
) {
    suspend operator fun invoke(barcode: String) =
        bottlesRepository.deleteBottleFromFirestore(barcode)
}
