package com.noxis.unittest.payment

/**
 * Сервис, который используется для отправки аналитических данных при оплате <br/>
 *
 *  - [paymentSuccess] используется для отправки аналитической метрики при успешной оплате
 *  - [paymentFailure] используется для отправки аналитической метрики при возникшей ошибки
 */
internal interface AnalyticsService {

    fun paymentSuccess(orderId: String, amount: Int)

    fun paymentFailure(orderId: String, amount: Int)
}