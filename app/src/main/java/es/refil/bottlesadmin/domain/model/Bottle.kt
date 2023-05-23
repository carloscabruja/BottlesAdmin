package es.refil.bottlesadmin.domain.model

data class Bottle(
    var barcode: String? = null,
    var points: Int? = null,
    var size: Int? = null,
    var type: String? = null,
)
