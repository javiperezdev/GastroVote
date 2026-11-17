package es.dam.gastrovote.javiperez.domain

import es.dam.gastrovote.javiperez.domain.model.TapaSize
import junit.framework.TestCase
import org.junit.Test

class CalculatePriceTest {
    @Test
    fun without_discount() {
        TestCase.assertEquals(10.0, calculatePrice(10.0, isStudent = false, tapaSize = TapaSize.TAPA), 0.001)
    }

    @Test
    fun without_discount_half_portion() {
        TestCase.assertEquals(20.0, calculatePrice(10.0, isStudent = false, tapaSize = TapaSize.HALF_PORTION), 0.001)
    }

    @Test
    fun with_discount_portion() {
        TestCase.assertEquals(27.0, calculatePrice(10.0, isStudent = true, tapaSize = TapaSize.PORTION), 0.001)
    }

    @Test
    fun with_discount() {
        TestCase.assertEquals(9.0, calculatePrice(10.0, isStudent = true, tapaSize = TapaSize.TAPA), 0.001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_price() {
        calculatePrice(0.0, isStudent = false, tapaSize = TapaSize.TAPA)
    }
}