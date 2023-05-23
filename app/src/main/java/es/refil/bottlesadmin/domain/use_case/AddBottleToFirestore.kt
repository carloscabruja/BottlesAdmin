package es.refil.bottlesadmin.domain.use_case

import es.refil.bottlesadmin.domain.repository.BottlesRepository

class AddBottleToFirestore(
    private val bottlesRepository: BottlesRepository
) {
    suspend operator fun invoke(
        barcode: String,
        points: Int,
        size: Int,
        type: String
    ) = bottlesRepository.addBottleToFirestore(barcode, points, size, type)
}
