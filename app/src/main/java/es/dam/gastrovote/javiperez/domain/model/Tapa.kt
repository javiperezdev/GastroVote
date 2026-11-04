package es.dam.gastrovote.javiperez.domain.model

data class Tapa (
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val rating: Int,
    val favourite: Boolean = false,
    val size: TapaSize = TapaSize.TAPA,
)

enum class TapaSize {
    TAPA,
    HALF_PORTION,
    PORTION
}