package payment.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PaymentRequest {

    @NotNull
    private final Long cartId;

    @Setter
    private BigDecimal value;
}
