package payment.application.controller.contract;

import org.springframework.web.bind.annotation.GetMapping;
import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentController {

    @GetMapping
    List<PaymentResponse> getAll();
}
