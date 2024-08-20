package com.noxis.unittest

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OtherCodeTest {

    @Test
    fun `fib(0) = 0`() {
        val result = OtherCode.fib(0)
        assertThat(result).isEqualTo(0)
    }


    @Test
    fun `fib(1) = 1`() {
        val result = OtherCode.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fib(n) = fib(n-2) + fib(n-1)`() {
        val result = OtherCode.fib(16)
        assertThat(result).isEqualTo(987)
    }


    @Test
    fun `(a * b)) return false`() {
        val result = OtherCode.checkBraces("(a * b))")
        assertThat(result).isFalse()
    }

    @Test
    fun `(a * b) return true`() {
        val result = OtherCode.checkBraces("(a * b)")
        assertThat(result).isTrue()
    }

    @Test
    fun `)a * b( return true`() {
        val result = OtherCode.checkBraces(")a * b(")
        assertThat(result).isFalse()
    }
}