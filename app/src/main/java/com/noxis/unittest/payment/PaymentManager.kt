package com.noxis.unittest.payment

/**
 * Менеджер оплаты. <br/>
 *
 * Метод [pay] используется для оплаты чего-либо. В случае успешной оплаты - отправится аналитическая
 * метрика об успешной оплате, иначе - аналитическая метрика об ошибке.
 */
internal class PaymentManager(
    private val paymentGateway: PaymentGateway,
    private val analytics: AnalyticsService
) {

    fun pay(orderId: String, amount: Int) {
        val isSuccess = paymentGateway.payProcess(amount)

        if (isSuccess) {
            analytics.paymentSuccess(orderId, amount)
        } else {
            analytics.paymentFailure(orderId, amount)
        }
    }
}