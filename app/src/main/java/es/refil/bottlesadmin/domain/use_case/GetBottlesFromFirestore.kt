package es.refil.bottlesadmin.domain.use_case

import es.refil.bottlesadmin.domain.repository.BottlesRepository

class GetBottlesFromFirestore(
    private val bottlesRepository: BottlesRepository
) {
    operator fun invoke() = bottlesRepository.getBottlesFromFirestore()

}
