package com.noxis.unittest.other

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Разрешить внешнее чтение, но не запись

    /**
     * Возвращает содержимое и предотвращает его повторное использование.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Возвращает содержимое, даже если оно уже было обработано.
     */
    fun peekContent(): T = content
}