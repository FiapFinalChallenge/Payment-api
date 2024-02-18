package payment.application.controller.contract;

import org.springframework.web.bind.annotation.*;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;

import java.util.List;

public interface IPaymentController {

    @GetMapping
    List<PaymentResponse> getAll();

    @GetMapping("{id}")
    PaymentResponse getById(@PathVariable Long id);

    @PostMapping
    PaymentResponse create(@RequestBody PaymentRequest paymentRequest);

    @PutMapping("{id}")
    PaymentResponse update(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest);

    @DeleteMapping("{id}")
    void deleteById(@PathVariable Long id);
}
