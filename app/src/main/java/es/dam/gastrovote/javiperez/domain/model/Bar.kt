package es.dam.gastrovote.javiperez.domain.model

data class Bar(
    val id: Int,
    val name: String,
    val tapa: Tapa,
    val phone: String,
    val address: String,
    val web: String
)
