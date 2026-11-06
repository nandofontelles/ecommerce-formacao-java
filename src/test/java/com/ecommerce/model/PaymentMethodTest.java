package com.ecommerce.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentMethodTest {
    @Test
    void testPaymentMethodDescription() {
        Assertions.assertEquals("PIX", Order.PaymentMethod.PIX.getDescription());
        Assertions.assertEquals("Cartao de Credito", Order.PaymentMethod.CREDIT_CARD.getDescription());
        Assertions.assertEquals("Cartao de Debito", Order.PaymentMethod.DEBIT_CARD.getDescription());
        Assertions.assertEquals("Boleto", Order.PaymentMethod.BOLETO.getDescription());
    }
}
