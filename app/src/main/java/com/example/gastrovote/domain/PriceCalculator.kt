package com.example.gastrovote.domain

private const val STUDENT_DISCOUNT_RATE = 0.10

internal fun calculate(basePrice: Double, isStudent: Boolean): Double {
    require(basePrice > 0) { "basePrice debe ser mayor que 0" }

    val discount = if (isStudent) basePrice * STUDENT_DISCOUNT_RATE else 0.0
    return basePrice - discount
}
