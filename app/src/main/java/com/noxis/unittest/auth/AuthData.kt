package com.noxis.unittest.auth

/**
 * Данные для авторизации
 *
 * @property username логин пользователя
 * @property password пароль пользователя
 */
internal data class AuthData(
    val username: String,
    val password: String
)