package es.dam.gastrovote.javiperez.domain.model

data class Tapa (
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val favourite: Boolean = false,
    val size: TapaSize = TapaSize.TAPA
    // val rating for further versions rating would go from 0-5
)

enum class TapaSize {
    TAPA,
    HALF_PORTION,
    PORTION
}