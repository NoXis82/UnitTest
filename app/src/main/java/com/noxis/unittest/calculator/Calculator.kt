package com.noxis.unittest.calculator

internal class Calculator {

    fun divideTwoNumbers(firstNumber: Double, secondNumber: Double): Double {

        if (secondNumber == 0.0) throw ArithmeticException("Делить на ноль нельзя!")
        return firstNumber / secondNumber
    }

    fun calculateTwoNumbers(first: Int, second: Int): Int {
        return first + second
    }
}