package payment.application.dto.response;

import payment.domain.enums.EPaymentStatus;

import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long cartId,
        BigDecimal value,
        EPaymentStatus status) {
}
