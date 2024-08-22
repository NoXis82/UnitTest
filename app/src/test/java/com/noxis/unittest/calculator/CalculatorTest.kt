package com.noxis.unittest.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `15 divide 5 equals 3`() {
        val firstNumber = 15.0
        val secondNumber = 5.0
        val expected = 3.0

        val actual = calculator.divideTwoNumbers(firstNumber, secondNumber)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `cannot divide by zero`() {
        val firstNumber = 15.0
        val secondNumber = 0.0
        val expectedMessage = "Делить на ноль нельзя!"

        assertThrows<ArithmeticException> {
            calculator.divideTwoNumbers(firstNumber, secondNumber)
        }.also { exception ->
            assertThat(exception.message).isEqualTo(expectedMessage)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1, 2, 3",
        "-1, 1, 0",
        "0, 0, 0",
        "10, -5, 5"
    )
    fun `test calculateTwoNumbers`(first: Int, second: Int, expected: Int) {
        val actual = calculator.calculateTwoNumbers(first, second)

        assertThat(actual).isEqualTo(expected)
    }
}