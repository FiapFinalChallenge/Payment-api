package payment.application.dto.request;

import java.math.BigDecimal;

public record PaymentRequest(Long cartId, BigDecimal value) {
}
