package payment.application.controller.contract;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentController {

    @GetMapping
    List<PaymentResponse> getAll();

    @GetMapping("{id}")
    PaymentResponse getPayment(@PathVariable Long id);

    @PostMapping
    PaymentResponse payment(@RequestBody @Valid PaymentRequest paymentRequest);

    @PutMapping("{id}/cancel")
    PaymentResponse cancelPayment(@PathVariable Long id);
}
