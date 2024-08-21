package com.noxis.unittest.auth

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test


class AuthManagerTest {
    private val authService = mockk<AuthService>()
    private val authManager = AuthManager(authService)


    @Test
    fun `login with correct auth data`() {
        val username = "correctUsername"
        val password = "correctPassword"
        val expectedToken = "token"
        val authData = AuthData(username, password)
        every { authService.auth(authData) } returns expectedToken

        val actualToken = authManager.login(username, password)

        assertThat(actualToken).isEqualTo(expectedToken)
        verify(exactly = 1) { authService.auth(authData) }
    }

    @Test
    fun `login with incorrect auth data`() {
        val username = "incorrectUsername"
        val password = "incorrectPassword"
        val authData = AuthData(username, password)
        every { authService.auth(authData) } returns null

        val actualToken = authManager.login(username, password)

        assertThat(actualToken).isNull()
        verify(exactly = 1) { authService.auth(authData) }
    }
}