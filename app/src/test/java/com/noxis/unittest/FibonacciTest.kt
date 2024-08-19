package com.noxis.unittest

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FibonacciTest {

    @Test
    fun `fib(0) = 0`() {
        val result = Fibonacci.fib(0)
        assertThat(result).isEqualTo(0)
    }


    @Test
    fun `fib(1) = 1`() {
        val result = Fibonacci.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fib(n) = fib(n-2) + fib(n-1)`() {
        val result = Fibonacci.fib(16)
        assertThat(result).isEqualTo(987)
    }

}