package payment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PaymentRequest {

    private final Long cartId;
    @Setter
    private BigDecimal value;
}
