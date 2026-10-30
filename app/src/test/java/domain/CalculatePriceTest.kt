package domain

import com.example.gastrovote.domain.calculate
import junit.framework.TestCase
import org.junit.Test

class CalculatePriceTest {
    @Test
    fun without_discount() {
        TestCase.assertEquals(10.0, calculate(10.0, isStudent = false), 0.001)
    }

    @Test
    fun with_discount() {
        TestCase.assertEquals(9.0, calculate(10.0, isStudent = true), 0.001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_price() {
        calculate(0.0, isStudent = false)
        }
}