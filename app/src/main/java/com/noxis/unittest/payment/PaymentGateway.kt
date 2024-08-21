package com.noxis.unittest.payment

/**
 * Cервис, который имитирует оплату
 */
internal interface PaymentGateway {
    fun payProcess(amount: Int): Boolean
}