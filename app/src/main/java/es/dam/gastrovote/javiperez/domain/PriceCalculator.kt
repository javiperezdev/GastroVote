package es.dam.gastrovote.javiperez.domain

import es.dam.gastrovote.javiperez.domain.model.TapaSize

private const val STUDENT_DISCOUNT_RATE = 0.10

internal fun calculate(basePrice: Double, isStudent: Boolean, tapaSize: TapaSize): Double {
    require(basePrice > 0) { "basePrice debe ser mayor que 0" }

    val multiplier = getMultiplier(tapaSize)
    val productPrice = basePrice * multiplier
    val discount = if (isStudent) productPrice * STUDENT_DISCOUNT_RATE else 0.0
    return productPrice - discount
}

internal fun getMultiplier(tapaSize: TapaSize): Int {
    return when (tapaSize) {
        TapaSize.TAPA -> 1
        TapaSize.HALF_PORTION -> 2
        TapaSize.PORTION -> 3
    }
}
