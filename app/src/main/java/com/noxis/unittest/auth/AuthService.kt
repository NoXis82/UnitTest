package com.noxis.unittest.auth

/**
 * Cервис, который имитирует запрос на удаленый сервис для авторизации
 */
internal interface AuthService {

    fun auth(authData: AuthData): String?
}