package com.noxis.unittest.auth

/**
 * Класс, который используется для авторизации. <br/>
 *
 * Метод [login] обращается к сервису, имитирующий запрос на удаленый сервис.
 * Если аутентификация прошла успешно - возвращается JWToken, иначе возвращает null.
 */
internal class AuthManager(private val authService: AuthService) {

    fun login(username: String, password: String): String? {
        return authService.auth(authData = AuthData(username, password))
    }
}